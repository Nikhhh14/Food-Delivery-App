# QuickBite Food Delivery - Project Workflow Guide

This document explains the complete working flow of the QuickBite Food Delivery application, describing exactly what happens behind the scenes for each major action.

---

## 1. User Registration & Login
**Goal**: Identify the user to manage their personal cart and order history.

*   **Registration**:
    1.  User fills out the registration form (`register.html`).
    2.  Data is sent to `RegisterServlet`.
    3.  The servlet saves the new user to the `users` table in the database.
*   **Login**:
    1.  User enters credentials (`login.html`).
    2.  `LoginServlet` checks the `users` database table.
    3.  If valid, a **Session** is created. This "Session" is a temporary memory on the server that remembers the user is logged in as they browse different pages.

---

## 2. Viewing the Menu
**Page**: `MenuServlet` (`/menu`)

When you open the Menu page:
1.  **Check Login**: The server first checks if you have an active Session. If not, it redirects you to Login.
2.  **Fetch Data**: `FoodItemDAO` queries the `food_items` table in the database to get all available dishes (Name, Price, Image, Stock).
3.  **Display**: The Java Servlet generates the HTML page, creating a "food-card" for each item found in the database.
4.  **Auto-Update Images**: On startup, the system automatically runs a quick check to ensure all image links in the database point to the correct local files in your `images/` folder.

---

## 3. Adding an Item to Cart ("Add to Cart")
**Action**: User clicks "Add to Cart" button.

1.  **Form Submission**: The browser sends a hidden request (POST) to `CartServlet` containing the `foodId` and `quantity` selected.
2.  **Retrieve Logic**:
    *   The server looks for an existing "Cart" list in your Session.
    *   If no cart exists yet, it creates a new empty list.
3.  **Update Logic**:
    *   It checks if the item is already in your cart.
    *   **If Yes**: It updates the quantity (e.g., changes 1 burger to 2 burgers).
    *   **If No**: It fetches the full food details from the database and adds a new item to your cart list.
4.  **Stock Check**: Before adding, it ensures the requested quantity doesn't exceed the `stock_quantity` available in the database.
5.  **Redirect**: After saving, the page reloads (`response.sendRedirect("menu")`) to show the updated status.

---

## 4. Viewing the Cart
**Page**: `CartServlet` (`/cart`)

When you click the "Cart" link:
1.  **Retrieve Cart**: The server gets the list of items saved in your Session.
2.  **Calculate Total**: It loops through every item (Price × Quantity) to calculate the final total bill.
3.  **Render Page**: It displays a table showing:
    *   Product Image (thumbnail)
    *   Name & Price
    *   Quantity selected
    *   Total Price per item
4.  **Layout**: The page is specially styled to be centered with a maximum width (`900px`) to focus your attention on the order details.

---

## 5. Placing an Order ("Checkout")
**Action**: User clicks "Place Order" in the Cart.

This is the most complex part involving a **Database Transaction** to ensure data safety.

1.  **Initialize**: `CheckoutServlet` prepares a new Order object with a unique Order ID (UUID).
2.  **Database Transaction Start**: The server tells the database "Wait, I'm about to do multiple things. Don't save anything permanently until I say verify all of them."
3.  **Step A - Save Order**: A new row is added to the `orders` table (Order ID, User Name, Total Amount).
4.  **Step B - Save Items**: The server loops through your cart and saves each item to the `order_items` table (linking it to the Order ID).
5.  **Step C - Reduce Stock**: For each item, the database `stock_quantity` is decreased by the amount you bought.
    *   *Safety Check*: If stock drops below 0 during this, the entire order is cancelled to prevent errors.
6.  **Commit Transaction**: If A, B, and C all succeed, the server "Commits" the changes, making them permanent.
7.  **Clear Cart**: The cart is removed from your Session since the order is confirmed.
8.  **Success Page**: You are shown the "Order Confirmed" page.

---

## 6. Viewing Order History ("My Orders")
**Page**: `OrderHistoryServlet` (`/orders`)

When you click "My Orders":
1.  **Query**: The server asks the database for all orders associated with your username.
2.  **Fetch Details**:
    *   It gets the main order info (Date, Total).
    *   It also queries the `order_items` table to find exactly what dishes were in each of those orders.
3.  **Display**: It renders a list of cards, each showing the order summary and valid thumbnails of the items you bought.

---

## Technical Summary
*   **Frontend**: HTML5, CSS3 (Custom styles `styles.css`), JavaScript (for cache busting).
*   **Backend**: Java Servlets (Control Logic).
*   **Database**: MySQL (Storage for Users, Menu, Orders).
*   **Communication**: Use JDBC (Java Database Connectivity) to securely talk to the database.
