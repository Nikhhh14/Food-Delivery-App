# QuickBite Food Delivery App

**QuickBite** is a full-featured food delivery web application offering a seamless ordering experience. Built with Java Servlets, JSP, and MySQL, it demonstrates a complete e-commerce flow from browsing a menu to placing secure orders.

## 🚀 Features

*   **User Authentication**: Secure Login and Registration system.
*   **Dynamic Menu**: Browse food items with rich visuals and descriptions.
*   **Shopping Cart**: Add items, update quantities, and view live total calculations. Validates stock availability.
*   **Order System**: Secure checkout process using **Database Transactions** to ensure data integrity.
*   **Order History**: Users can track their past orders and see detailed item breakdowns.
*   **Responsive UI**: Modern, clean interface designed with custom CSS (Glassmorphism, sticky headers, responsive grid).

## 🛠️ Technologies Used

*   **Backend**: Java Servlets (Jakarta EE), JDBC.
*   **Frontend**: HTML5, CSS3, JSP, JSTL.
*   **Database**: MySQL.
*   **Build Tool**: Maven.
*   **Server**: Jetty (via Maven Plugin).

## 📚 Documentation

We have detailed documentation available for both users and developers:

*   **[Project Workflow Guide](PROJECT_WORKFLOW.md)**: A step-by-step walkthrough of how the application works from a user's perspective.
*   **[Code Explanation](CODE_EXPLANATION.md)**: A deep dive into the code logic, explaining *why* we used Sessions, Transactions, and Joins.

## ⚙️ Setup & Installation

### Prerequisites
*   Java JDK 11 or higher.
*   Maven installed.
*   MySQL Server installed and running.

### 1. Database Setup
1.  Open MySQL Workbench or your terminal.
2.  Create a new database named `food_delivery_db`.
    ```sql
    CREATE DATABASE food_delivery_db;
    ```
3.  Run the `src/main/resources/schema.sql` script to create tables and seed initial data.
4.  Update your database credentials in `src/main/resources/db.properties`:
    ```properties
    db.url=jdbc:mysql://localhost:3306/food_delivery_db
    db.user=YOUR_USERNAME
    db.password=YOUR_PASSWORD
    ```

### 2. Run the Application
1.  Open a terminal in the project root folder.
2.  Run the application using Maven:
    ```bash
    mvn jetty:run
    ```
3.  Access the app in your browser at:
    **[http://localhost:8081/fooddelivery](http://localhost:8081/fooddelivery)**

## 📂 Project Structure

```
FoodDeliveryApp/
├── src/main/java/com/fooddelivery/
│   ├── dao/          # Database Access Objects (Data Layer)
│   ├── model/        # Java Classes (POJOs)
│   ├── servlets/     # Controllers (Logic Layer)
│   └── util/         # Helpers (DB Connection)
├── src/main/webapp/
│   ├── css/          # Stylesheets
│   ├── images/       # Food Images
│   └── WEB-INF/      # JSP Files and Config
└── pom.xml           # Project Dependencies
```

## 📝 License

This project is created for educational purposes. Feel free to use and modify it.
