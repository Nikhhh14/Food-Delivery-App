# PROJECT_A_TO_Z_GUIDE.md

## 1️⃣ Project Overview

**What is this?**
This is a **Food Delivery Web Application** (like Swiggy or Zomato) where users can register, browse food items from restaurants, add them to a shopping cart, and place orders.

**Features:**
*   **User Module**: Register, Login, Logout.
*   **Menu**: View detailed food items with prices and images.
*   **Cart**: Add multiple items, calculate total bill.
*   **Order**: Securely place orders (simulated checkout).
*   **History**: View past order details.

**Technologies Used:**
*   **Java (JDK 11+)**: Backend logic.
*   **Servlets & JSP**: Handling requests and displaying pages (MVC).
*   **JDBC**: Talking to the database.
*   **MySQL**: Storing data.
*   **HTML/CSS**: Frontend design.
*   **Apache Tomcat**: The server to run the app.

---

## 2️⃣ Environment Setup (FIRST STEP)

Before writing code, prepare your computer.

1.  **Install Java JDK**: Download JDK 11 or 17. Set `JAVA_HOME` environment variable.
2.  **Install MySQL Server**: Download MySQL Community Server and MySQL Workbench. Remember your password (e.g., `root`).
3.  **Install Apache Tomcat**: Download Tomcat 9 or 10. This is your "Web Server".
4.  **Install IDE**: Use **Eclipse IDE for Enterprise Java Developers** or **IntelliJ IDEA Ultimate**.
5.  **Create Project**:
    *   In Eclipse: `File > New > Dynamic Web Project`.
    *   Name it: `FoodDeliveryApp`.
6.  **Dependencies (JARS)**:
    *   You need `mysql-connector-j-8.x.jar` (for DB).
    *   You need `jakarta.servlet-api.jar` (usually built-in to Tomcat).
    *   Put the MySQL jar in `src/main/webapp/WEB-INF/lib`.

---

## 3️⃣ Database Creation (SECOND STEP)

Open MySQL Workbench and run these commands to set up storage.

```sql
CREATE DATABASE food_delivery_db;
USE food_delivery_db;

-- 1. Users Table
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(100) NOT NULL,
    address TEXT
);

-- 2. Restaurants Table
CREATE TABLE restaurants (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cuisine VARCHAR(50),
    rating DECIMAL(2,1),
    is_active BOOLEAN DEFAULT TRUE,
    image_path VARCHAR(255)
);

-- 3. Menu Table
CREATE TABLE menu (
    id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    price DECIMAL(10,2) NOT NULL,
    ratings DECIMAL(2,1),
    is_available BOOLEAN DEFAULT TRUE,
    image_path VARCHAR(255),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- 4. Orders Table
CREATE TABLE orders (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    restaurant_id INT,
    total_amount DECIMAL(10,2),
    status VARCHAR(50) DEFAULT 'Pending',
    order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    payment_mode VARCHAR(50),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

-- 5. Order Items (Items inside an order)
CREATE TABLE order_items (
    order_item_id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT,
    menu_id INT,
    quantity INT NOT NULL,
    item_total DECIMAL(10,2),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (menu_id) REFERENCES menu(id)
);
```

---

## 4️⃣ Project Folder Structure (THIRD STEP)

Organization is key. Create these packages inside `src/main/java`:

1.  `com.food.model`: **POJO Classes**. Simple Java classes with fields only.
2.  `com.food.dao`: **Interfaces**. Lists "WHAT" actions we can do (e.g., `insertUser`).
3.  `com.food.daoimpl`: **Classes**. Contains the SQL code implementing the DAO interfaces.
4.  `com.food.util`: **Helper**. Database connection logic.
5.  `com.food.servlet`: **Controllers**. Servlets that handle browser requests.

---

## 5️⃣ Model Classes (FOURTH STEP)

Create these standard Java classes in `com.food.model`. They carry data.

1.  **`User.java`**:
    *   Fields: `id`, `username`, `password`, `email`, `address`.
    *   Generate: Constructor (Empty & Full), Getters, Setters, `toString()`.
2.  **`Restaurant.java`**:
    *   Fields: `id`, `name`, `cuisine`, `rating`, `imagePath`.
3.  **`Menu.java`**:
    *   Fields: `id`, `restaurantId`, `name`, `price`, `description`, `imagePath`.
4.  **`CartItem.java`**:
    *   Fields: `itemId`, `restaurantId`, `name`, `price`, `quantity`, `subTotal`.
5.  **`Order.java`**:
    *   Fields: `orderId`, `userId`, `totalAmount`, `status`, `date`.

---

## 6️⃣ DB Connection Utility (FIFTH STEP)

Create `DBConnection.java` in `com.food.util`.

*   **Variables**: `URL` ("jdbc:mysql://localhost:3306/food_delivery_db"), `USERNAME`, `PASSWORD`.
*   **Method**: `getConnection()`
    *   Load Driver: `Class.forName("com.mysql.cj.jdbc.Driver");`
    *   Return: `DriverManager.getConnection(url, user, pwd);`

---

## 7️⃣ DAO Interfaces (SIXTH STEP)

Create these Interfaces in `com.food.dao`. This is the **Design Pattern**. It separates "Logic" from "Database Code".

1.  **`UserDAO.java`**:
    *   `int insert(User user);`
    *   `User fetchByEmail(String email);`
2.  **`RestaurantDAO.java`**:
    *   `List<Restaurant> fetchAll();`
3.  **`MenuDAO.java`**:
    *   `List<Menu> fetchByRestaurant(int restaurantId);`
    *   `Menu fetchById(int menuId);`
4.  **`OrderDAO.java`**:
    *   `int insert(Order order);`

---

## 8️⃣ DAOImpl Classes (SEVENTH STEP)

Create Classes in `com.food.daoimpl` implementing the interfaces. This is where SQL lives.

1.  **`UserDAOImpl.java`**:
    *   Implement `insert()`: Use `PreparedStatement` with `INSERT INTO users...`.
    *   Implement `fetchByEmail()`: Use `SELECT * FROM users WHERE email=?`.
2.  **`RestaurantDAOImpl.java`**:
    *   Implement `fetchAll()`: Use `SELECT * FROM restaurants`. Loop `ResultSet` and add to List.
3.  **`MenuDAOImpl.java`**:
    *   Implement `fetchByRestaurant()`: Select menu items for a specific restaurant ID.
4.  **`OrderDAOImpl.java`**:
    *   Implement `insert()`: **Critical**. Insert into `orders` table. Then get generated ID. Then insert cart items into `order_items` table.

---

## 9️⃣ Servlets Creation Order (VERY IMPORTANT)

Servlets bridge the Frontend (JSP) and Backend (DAO). Create in `com.food.servlet`.

1.  **`RegisterServlet.java`**:
    *   `doPost`: Get params (name, email...) -> Create `User` object -> Call `UserDAOImpl.insert()`. Redirect to `login.jsp`.
2.  **`LoginServlet.java`**:
    *   `doPost`: Get params -> Call `UserDAOImpl.verify()`.
    *   **Success**: Create `HttpSession session = request.getSession()`. Save User in session. Redirect to `home.jsp`.
3.  **`HomeServlet.java` (Restaurant List)**:
    *   `doGet`: Call `RestaurantDAOImpl.fetchAll()`. Store list in `request.setAttribute`. Forward to `home.jsp`.
4.  **`MenuServlet.java`**:
    *   `doGet`: Get `restaurantId` param. Call `MenuDAOImpl.fetchByRestaurant()`. Store list. Forward to `menu.jsp`.
5.  **`CartServlet.java`**:
    *   `doPost`: "Add to Cart". Get `Menu` Item from DAO. Add to `Map<Integer, CartItem>` in Session.
         *   *Rule*: Check if Session `cart` exists. If not, create new `HashMap`.
6.  **`OrderServlet.java`**:
    *   `doPost`: "Place Order". Get Cart from Session. Call `OrderDAOImpl.insert()`. Clear Cart. Redirect to `confirm.jsp`.

---

## 🔟 JSP Pages Creation

Create these in `src/main/webapp/`.

1.  **`login.jsp`**: Form (Email, Password) -> action="login".
2.  **`register.jsp`**: Form (Name, Email, Pass) -> action="register".
3.  **`home.jsp`**: Import `List<Restaurant>`. Use For-Each loop to display restaurant Cards. Link to `menu?restaurantId=...`.
4.  **`menu.jsp`**: Loop through Menu list. "Add to Cart" button -> form action="cart".
5.  **`cart.jsp`**: Get Session Cart. Display Table (Item, Qty, Price). "Checkout" button.
6.  **`checkout.jsp`**: Final Summary. "Pay Now" button -> action="order".

---

## 1️⃣1️⃣ Session Management

**How it works**:
*   When a user logs in, `LoginServlet` says: `session.setAttribute("loggedInUser", userObject)`.
*   On every other page (Cart, Order), use `session.getAttribute("loggedInUser")` to check if someone is logged in.
*   The **Cart** is also stored here: `session.setAttribute("cart", cartMap)`. This keeps items saved even if you refresh!

---

## 1️⃣2️⃣ MVC Flow Explanation (The Summary)

1.  **User** clicks link (View Menu).
2.  **JSP** sends request to **MenuServlet** (Controller).
3.  **Servlet** asks **MenuDAO** (Model Layer) for data.
4.  **DAO** runs SQL Query on **Database**.
5.  **Database** gives result back to DAO.
6.  **DAO** gives Java Objects (`List<Menu>`) to Servlet.
7.  **Servlet** puts data in `request` scope and forwards to **menu.jsp**.
8.  **JSP** formats data as HTML for the User.

---

## 1️⃣3️⃣ Testing Steps (END)

1.  Right-click Project > Run As > **Run on Server**.
2.  Open Browser: `localhost:8080/FoodDeliveryApp`.
3.  **Register** a dummy user.
4.  **Login** with that user.
5.  **Home Page**: Do you see restaurants? (If not, check your DB data).
6.  **Menu**: Click a restaurant. Do you see food?
7.  **Cart**: Add "Pizza". Go to Cart. Is it there?
8.  **Checkout**: Click Pay. check `orders` table in MySQL.

**Congratulations! You have built a complete Enterprise Java Web App from scratch!** 🚀
