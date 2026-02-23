package com.food.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataSeeder {

    public static void main(String[] args) {
        seedData();
    }

    public static void seedData() {
        System.out.println("🌱 Starting Data Seeding...");

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Failed to connect to database");
                return;
            }
            conn.setAutoCommit(false); // Transaction mode

            // 1. Burger King
            int r1 = insertRestaurant(conn, "Burger King", "American", 4.5, "https://images.unsplash.com/photo-1571091718767-18b5b1457add?w=500&q=80");
            if (r1 != -1) {
                insertMenu(conn, r1, "Whopper", "Flame-grilled beef patty, topped with tomatoes, fresh cut lettuce, mayo, pickles, a swirl of ketchup, and sliced white onions on a soft sesame seed bun.", 199, "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=500&q=80");
                insertMenu(conn, r1, "Cheesy Fries", "Crispy french fries topped with melted cheese and bacon bits.", 129, "https://images.unsplash.com/photo-1585109649139-366815a0d713?w=500&q=80");
                insertMenu(conn, r1, "Coke Zero", "Refreshing Coca-Cola Zero Sugar.", 79, "https://images.unsplash.com/photo-1622483767028-3f66f32aef97?w=500&q=80");
            }

            // 2. Pizza Hut
            int r2 = insertRestaurant(conn, "Pizza Hut", "Italian", 4.2, "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=500&q=80");
            if (r2 != -1) {
                insertMenu(conn, r2, "Pepperoni Feast", "Classic mozzarella cheese and pepperoni slices.", 499, "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=500&q=80");
                insertMenu(conn, r2, "Garlic Bread", "Toasted french bread topped with garlic butter and herbs.", 149, "https://images.unsplash.com/photo-1573140247632-f84660f67127?w=500&q=80");
                insertMenu(conn, r2, "Pasta Creamy Alfredo", "Penne pasta tossed in creamy alfredo sauce with mushrooms.", 349, "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=500&q=80");
            }

            // 3. Sushi Master
            int r3 = insertRestaurant(conn, "Sushi Master", "Japanese", 4.8, "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=500&q=80");
            if (r3 != -1) {
                insertMenu(conn, r3, "Salmon Roll", "Fresh salmon, avocado, and cucumber wrapped in sushi rice and seaweed.", 399, "https://images.unsplash.com/photo-1579584425555-c3ce17fd43fb?w=500&q=80");
                insertMenu(conn, r3, "Tuna Sashimi", "Premium cuts of fresh tuna.", 499, "https://images.unsplash.com/photo-1534482421-64566f976cfa?w=500&q=80");
                insertMenu(conn, r3, "Miso Soup", "Traditional Japanese soup with tofu and seaweed.", 99, "https://images.unsplash.com/photo-1547592166-23ac45744acd?w=500&q=80");
            }

            // 4. Taco Bell
            int r4 = insertRestaurant(conn, "Taco Bell", "Mexican", 4.0, "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=500&q=80");
            if (r4 != -1) {
                insertMenu(conn, r4, "Crunchy Taco", "Crunchy corn shell filled with seasoned beef, lettuce, and cheese.", 99, "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?w=500&q=80");
                insertMenu(conn, r4, "Burrito Supreme", "Soft flour tortilla filled with beef, beans, cheese, and sour cream.", 249, "https://images.unsplash.com/photo-1566740933430-b5e70b06d2d5?w=500&q=80");
                insertMenu(conn, r4, "Nachos BellGrande", "Crispy tortilla chips topped with cheese sauce, beef, tomatoes, and sour cream.", 299, "https://images.unsplash.com/photo-1519225421980-715cb0202128?w=500&q=80");
            }

            // 5. KFC
            int r5 = insertRestaurant(conn, "KFC", "Fast Food", 4.1, "https://images.unsplash.com/photo-1513639776629-7b611594e29b?w=500&q=80");
            if (r5 != -1) {
                insertMenu(conn, r5, "Fried Chicken Bucket", "12 pieces of our signature original recipe fried chicken.", 699, "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=500&q=80");
                insertMenu(conn, r5, "Zinger Burger", "Crispy chicken fillet with lettuce and mayo in a sesame bun.", 179, "https://images.unsplash.com/photo-1594212699903-ec8a3eca50f5?w=500&q=80");
                insertMenu(conn, r5, "Popcorn Chicken", "Bite-sized pieces of tender chicken breast breaded and fried.", 129, "https://images.unsplash.com/photo-1569691105751-88df003de7a4?w=500&q=80");
            }

            // 6. Subway
            int r6 = insertRestaurant(conn, "Subway", "Healthy", 4.3, "https://images.unsplash.com/photo-1554433607-66b5efe9d304?w=500&q=80");
            if (r6 != -1) {
                insertMenu(conn, r6, "Italian B.M.T.", "Sliced Genoa salami, pepperoni, and ham with your choice of vegetables.", 229, "https://images.unsplash.com/photo-1603903631889-b5f3ba4d436e?w=500&q=80");
                insertMenu(conn, r6, "Veggie Delite", "A fresh combination of lettuce, tomatoes, green peppers, cucumbers, and onions.", 149, "https://images.unsplash.com/photo-1553909489-cdb355cf7006?w=500&q=80");
                insertMenu(conn, r6, "Tuna Sub", "Flaked tuna mixed with mayo, balanced with fresh crisp veggies.", 249, "https://images.unsplash.com/photo-1509722747741-0add5fd38c6d?w=500&q=80");
            }

            // 7. Curry House
            int r7 = insertRestaurant(conn, "Curry House", "Indian", 4.7, "https://images.unsplash.com/photo-1585937421612-70a008356f36?w=500&q=80");
            if (r7 != -1) {
                insertMenu(conn, r7, "Butter Chicken", "Tender chicken cooked in a rich and creamy tomato-based sauce.", 349, "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=500&q=80");
                insertMenu(conn, r7, "Paneer Tikka Masala", "Grilled paneer cubes gently simmered in a spicy curry sauce.", 299, "https://images.unsplash.com/photo-1565557623262-b51c2513a641?w=500&q=80");
                insertMenu(conn, r7, "Garlic Naan", "Soft indian bread topped with minced garlic and coriander.", 59, "https://images.unsplash.com/photo-1626074353765-517a681e40be?w=500&q=80");
            }

            // 8. Dessert Haven
            int r8 = insertRestaurant(conn, "Dessert Haven", "Dessert", 4.9, "https://images.unsplash.com/photo-1551024601-564d6dbf4302?w=500&q=80");
            if (r8 != -1) {
                insertMenu(conn, r8, "Chocolate Lava Cake", "Molten chocolate cake with a gooey center, served with vanilla ice cream.", 199, "https://images.unsplash.com/photo-1606313564200-e75d5e30476d?w=500&q=80");
                insertMenu(conn, r8, "New York Cheesecake", "Classic creamy cheesecake with a graham cracker crust.", 249, "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?w=500&q=80");
                insertMenu(conn, r8, "Macarons", "Assorted french macarons in various flavors.", 299, "https://images.unsplash.com/photo-1569864358637-2d1d544bc175?w=500&q=80");
            }

            // 9. Domino's Pizza
            int r9 = insertRestaurant(conn, "Domino's Pizza", "Italian", 4.1, "https://images.unsplash.com/photo-1628840042765-356cda07504e?w=500&q=80");
            if (r9 != -1) {
                insertMenu(conn, r9, "Farmhouse Pizza", "Delightful combination of onion, capsicum, tomato & grilled mushroom.", 459, "https://images.unsplash.com/photo-1513104890138-7c749659a591?w=500&q=80");
                insertMenu(conn, r9, "Peppy Paneer", "Flavorful trio of juicy paneer, crisp capsicum with spicy red paprika.", 399, "https://images.unsplash.com/photo-1565299624946-b28f40a0ae38?w=500&q=80");
                insertMenu(conn, r9, "Choco Lava Cake", "Chocolate lover's delight! Molten chocolate cake.", 99, "https://images.unsplash.com/photo-1606313564200-e75d5e30476d?w=500&q=80");
            }

            // 10. Starbucks
            int r10 = insertRestaurant(conn, "Starbucks", "Cafe", 4.6, "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=500&q=80");
            if (r10 != -1) {
                insertMenu(conn, r10, "Java Chip Frappuccino", "Mocha sauce and Frappuccino chips blended with coffee and milk.", 299, "https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=500&q=80");
                insertMenu(conn, r10, "Caramel Macchiato", "Freshly steamed milk with vanilla-flavored syrup marked with espresso.", 249, "https://images.unsplash.com/photo-1485808191679-5f86510681a2?w=500&q=80");
                insertMenu(conn, r10, "Croissant", "Flaky and buttery golden croissant.", 149, "https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=500&q=80");
            }

            // 11. Dunkin' Donuts
            int r11 = insertRestaurant(conn, "Dunkin' Donuts", "Dessert", 4.3, "https://images.unsplash.com/photo-1551024506-0bccd828d307?w=500&q=80");
            if (r11 != -1) {
                insertMenu(conn, r11, "Glazed Donut", "Classic ring donut with a sweet glaze.", 89, "https://images.unsplash.com/photo-1631405021272-972c4c375525?w=500&q=80");
                insertMenu(conn, r11, "Chocolate Frosted", "Donut topped with rich chocolate icing.", 99, "https://images.unsplash.com/photo-1551325605-64906f2df6b1?w=500&q=80");
                insertMenu(conn, r11, "Iced Coffee", "Smooth and refreshing iced coffee.", 159, "https://images.unsplash.com/photo-1517701604599-bb29b5dd7359?w=500&q=80");
            }

            // 12. Baskin Robbins
            int r12 = insertRestaurant(conn, "Baskin Robbins", "Ice Cream", 4.7, "https://images.unsplash.com/photo-1563805042-7684c019e1cb?w=500&q=80");
            if (r12 != -1) {
                insertMenu(conn, r12, "Cotton Candy", "Sweet and colorful cotton candy flavored ice cream.", 129, "https://images.unsplash.com/photo-1505394033641-40c6ad1178d1?w=500&q=80");
                insertMenu(conn, r12, "Mint Chocolate Chip", "Mint flavored ice cream with chocolate chips.", 139, "https://images.unsplash.com/photo-1551327170-d2932f93539e?w=500&q=80");
                insertMenu(conn, r12, "Banana Split", "Classic sundae with banana, ice cream, and toppings.", 249, "https://images.unsplash.com/photo-1579954115545-a95591f28dfc?w=500&q=80");
            }

            // 13. Paradise Biryani
            int r13 = insertRestaurant(conn, "Paradise Biryani", "Indian", 4.4, "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=500&q=80");
            if (r13 != -1) {
                insertMenu(conn, r13, "Hyderabadi Chicken Biryani", "Aromatic basmati rice cooked with tender chicken and spices.", 349, "https://images.unsplash.com/photo-1589302168068-964664d93dc0?w=500&q=80");
                insertMenu(conn, r13, "Mutton Biryani", "Rich and flavorful biryani made with succulent mutton.", 449, "https://images.unsplash.com/photo-1633945274405-b6c8069047b0?w=500&q=80");
                insertMenu(conn, r13, "Mirchi Ka Salan", "Tangy and spicy chili curry, perfect accompaniment for biryani.", 99, "https://images.unsplash.com/photo-1601050690597-df0568f70950?w=500&q=80");
            }

            conn.commit();
            System.out.println("✅ Data Seeding Completed Successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int insertRestaurant(Connection conn, String name, String cuisine, double rating, String imagePath) throws Exception {
        // NOTE: 'is_active' column is inferred from RestaurantDAOImpl usage (WHERE is_active=TRUE)
        // If this column doesn't exist, remove it from the query.
        String sql = "INSERT INTO restaurants (name, cuisine, rating, image_path, is_active) VALUES (?, ?, ?, ?, 1)";
        try (PreparedStatement console = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            console.setString(1, name);
            console.setString(2, cuisine);
            console.setDouble(3, rating);
            console.setString(4, imagePath);
            console.executeUpdate();

            try (ResultSet rs = console.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return -1;
    }

    private static void insertMenu(Connection conn, int restaurantId, String name, String description, double price, String imagePath) throws Exception {
        // NOTE: 'is_available' column is inferred from MenuDAOImpl usage.
        String sql = "INSERT INTO menu (restaurant_id, name, description, price, image_path, is_available) VALUES (?, ?, ?, ?, ?, 1)";
        try (PreparedStatement console = conn.prepareStatement(sql)) {
            console.setInt(1, restaurantId);
            console.setString(2, name);
            console.setString(3, description);
            console.setDouble(4, price);
            console.setString(5, imagePath);
            console.executeUpdate();
        }
    }
}
