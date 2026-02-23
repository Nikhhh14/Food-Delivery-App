# Deep Dive: Code-Level Explanation

This document explains the **"Why"** and **"How"** behind the code. It answers "What happens when we click this?" and "Why did we write the code this way?"

---

## 1. The "Add to Cart" Logic

### Scenario: You click the "Add to Cart" button.

**1. The Trigger (MenuServlet.java)**
We don't just use a simple link; we use a `<form>`.
```html
<!-- Inside MenuServlet.java -->
<form action='cart' method='post'>
    <input type='hidden' name='foodId' value='101'>
    <input type='number' name='quantity' value='1'>
    <button type='submit'>Add to Cart</button>
</form>
```
*   **What happens here?** The `action='cart'` tells the browser: "Package up these inputs and send them to the `CartServlet`."
*   **Why `type='hidden'`?** We need to tell the server *which* food ID to add (e.g., `101`), but the user doesn't need to see that number.

**2. The Receiver (CartServlet.java)**
The server catches this request in the `doPost` method.
```java
// CartServlet.java
int foodId = Integer.parseInt(request.getParameter("foodId"));
int quantity = Integer.parseInt(request.getParameter("quantity"));

// "Get the Session"
HttpSession session = request.getSession();
List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
```
*   **What is `session`?** This is the **most important** part of the cart. The web is "stateless" (it forgets who you are after every click). The `Session` is a special memory locker on the server assigned to *you* specifically.
*   **Why do we do this?** If we didn't use Session, every time you refreshed the page, your cart would vanish.
*   **The Logic:**
    1.  We look in the locker: "Does this user already have a `cart` list?"
    2.  **If No**: Create a `new ArrayList<>()` (Start a fresh cart).
    3.  **If Yes**: Use the existing list.

---

## 2. The "Place Order" Logic (Transactions)

### Scenario: You click "Place Order" in the cart.

This is where we use **Database Transactions**. This is critical for data safety.

**1. The Controller (CheckoutServlet.java)**
```java
// CheckoutServlet.java
Order order = new Order(..., total);
orderDAO.saveOrder(order); // Call the database code
session.removeAttribute("cart"); // IMPORTANT: Clear the cart only AFTER saving!
```
*   **Why clear the cart here?** Once the order is safe in the database, we must empty the user's shopping bag so they don't buy it twice by mistake.

**2. The Database Work (OrderDAOImpl.java)**
This is "doing it like this" (The Professional Way) vs "doing it simply" (The Risky Way).

```java
// OrderDAOImpl.java

// STEP 1: Turn OFF auto-save
conn.setAutoCommit(false);

try {
    // STEP 2: Save the Order Receipt
    stmt.executeUpdate("INSERT INTO orders ...");

    // STEP 3: Save the Items
    for (CartItem item : items) {
        stmt.executeUpdate("INSERT INTO order_items ...");

        // STEP 4: Update Stock
        int rows = stmt.executeUpdate("UPDATE food_items SET stock = stock - qty ...");
        if (rows == 0) throw new SQLException("Out of Stock!");
    }

    // STEP 5: The Grand Finale
    conn.commit(); // Save EVERYTHING permanently

} catch (Exception e) {
    // STEP 6: The Safety Net
    conn.rollback(); // Undo EVERYTHING if even one tiny thing failed
}
```

### "What happens when we do it like this?" (With Transaction)
Imagine you order a Burger, but the Database crashes exactly after **Step 2** (Saving the Order) but before **Step 3** (Saving the Items).
*   **With `conn.rollback()`**: The database sees the crash, realizes you didn't finish, and **deletes** the incomplete Order. No harm done. You can try again.

### "What happens if we didn't do this?" (Without Transaction)
If we left `AutoCommit(true)` (the default):
1.  It saves the "Order" row. **Saved.**
2.  Database crashes before saving "Items".
3.  **Result**: You have an Order #123 in the system for $50, but it has **ZERO items** in it. The stock wasn't reduced. The money might be taken, but we don't know what to deliver. This is a "corrupted state."

---

## 3. The "My Orders" Logic (Joins)

### Scenario: Viewing Order History.

**1. The Query (OrderDAOImpl.java)**
We don't just ask for the order; we need the details.
```java
String sql = "SELECT * FROM order_items oi " +
             "JOIN food_items f ON oi.food_id = f.id " +
             "WHERE oi.order_id = ?";
```
*   **Why `JOIN`?**
    *   `order_items` table only knows: "Order #1 bought Item #5". It *doesn't* know the name "Burger" or the Image URL.
    *   `food_items` table knows: "Item #5 is a Burger".
    *   The `JOIN` connects them: "Hey details, grab the name and image from the other table for Item #5."

**2. The Display (OrderHistoryServlet.java)**
```java
// OrderHistoryServlet.java
for (Order order : orders) {
    // Print Order ID, Total...
    for (CartItem item : order.getItems()) {
        // Print Image, Name, Qty...
    }
}
```
*   **What happens here?** It's a loop inside a loop.
    *   Outer Loop: Goes through each Receipt (Order).
    *   Inner Loop: Goes through each Line Item on that receipt.
