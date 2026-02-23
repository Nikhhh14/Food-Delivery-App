package com.food.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/food_delivery?useSSL=false&serverTimezone=UTC";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "atpchz6aek"; // 👉 change to your mysql password

    private static Connection connection;

    // Private constructor (Singleton style)
    private DBConnection() {}

    public static Connection getConnection() {

        try {
            if (connection == null || connection.isClosed()) {

                // Load Driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create Connection
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                System.out.println("✅ Database Connected Successfully");
            }
        } 
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
