# Step-by-Step Implementation Guide

This guide follows the exact coding order: **Configuration -> Models -> DAO -> Servlets -> Frontend**.

---

## 🛑 Step 0: The Setup (Infrastructure)
Before coding Java, get the environment ready.
1.  **Create Project**: Run `mvn archetype:generate ...` or create folder `FoodDeliveryApp`.
2.  **Verify `pom.xml`**: Add dependencies (`mysql-connector-j`, `jakarta.servlet-api`, `jstl`).
3.  **Database**:
    *   Create `db.properties` in `src/main/resources`.
    *   Initialize MySQL database `food_delivery_db` and tables.

---

## 🏗️ Step 1: The Connection (The Bridge)
We need a way for Java to talk to the Database.
*   **Create**: `com.fooddelivery.util.DBConnection.java`
*   **Logic**: Read `db.properties` and return a `Connection` object using `DriverManager.getConnection()`.

---

## 📦 Step 2: The Models (Data Carriers)
Create simple Java classes (POJOs) to hold data. These match your Database tables.
1.  **Create**: `com.fooddelivery.model.User.java` (fields: id, username, password...)
2.  **Create**: `com.fooddelivery.model.FoodItem.java` (fields: id, name, price, image...)
3.  **Create**: `com.fooddelivery.model.CartItem.java` (fields: FoodItem, quantity)
4.  **Create**: `com.fooddelivery.model.Order.java` (fields: orderId, list of items, total...)

---

## ⚙️ Step 3: The DAO Interfaces (The Contracts)
Define **WHAT** we can do with the database, but not **HOW** yet.
1.  **Create**: `com.fooddelivery.dao.UserDAO.java`
    *   `void save(User user);`
    *   `User findByUsername(String username);`
2.  **Create**: `com.fooddelivery.dao.FoodItemDAO.java`
    *   `List<FoodItem> getAllItems();`
    *   `FoodItem getById(int id);`
3.  **Create**: `com.fooddelivery.dao.OrderDAO.java`
    *   `void saveOrder(Order order);`
    *   `List<Order> getOrdersByUsername(String user);`

---

## 🛠️ Step 4: The DAO Implementations (The Workers)
Now write the SQL code. This uses **Step 1 (DBConnection)** and returns **Step 2 (Models)**.
1.  **Create**: `com.fooddelivery.dao.UserDAOImpl.java`
    *   Write SQL: `INSERT INTO users...`
2.  **Create**: `com.fooddelivery.dao.FoodItemDAOImpl.java`
    *   Write SQL: `SELECT * FROM food_items...`
3.  **Create**: `com.fooddelivery.dao.OrderDAOImpl.java`
    *   Write SQL: `INSERT INTO orders...` (use Transactions here!)

---

## 🎮 Step 5: The Servlets (The Controllers)
These handle user clicks. They use **Step 4 (DAO Impl)**.

### A. Authentication
1.  **Create**: `RegisterServlet.java`
    *   Call `UserDAOImpl.save()`.
2.  **Create**: `LoginServlet.java`
    *   Call `UserDAOImpl.findByUsername()`.
    *   If correct, create `HttpSession session = request.getSession();`

### B. Core Features
3.  **Create**: `MenuServlet.java`
    *   Call `FoodItemDAOImpl.getAllItems()`.
    *   Display HTML list of food.
4.  **Create**: `CartServlet.java`
    *   Get `session`. Retrieve `ArrayList<CartItem>`.
    *   Add selected item to the list.
5.  **Create**: `CheckoutServlet.java`
    *   Call `OrderDAOImpl.saveOrder()`.
    *   Clear session cart after success.
6.  **Create**: `OrderHistoryServlet.java`
    *   Call `OrderDAOImpl.getOrdersByUsername()`.

---

## 🎨 Step 6: The Frontend (The Look)
1.  **Create**: `styles.css` (Colors, layout).
2.  **Create**: `register.html` & `login.html` (Forms submitting to servlets).
3.  **Servlets**: Ensure your Servlets output proper HTML (or use JSPs).

---

## 🔄 Summary of Workflow
*   **User** clicks Javascript/HTML.
*   **Servlet** receives request.
*   **Servlet** calls **DAO**.
*   **DAO** uses **DBConnection** to talk to MySQL.
*   **Database** returns data.
*   **DAO** packages data into **Model**.
*   **Servlet** sends **Model** data back to User's screen.
