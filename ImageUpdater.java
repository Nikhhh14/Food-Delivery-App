package com.food.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ImageUpdater {

    // Keyword -> Image URL mapping
    private static final Map<String, String> IMAGE_MAP = new HashMap<>();

    static {
        // Restaurants / General Categories
        IMAGE_MAP.put("pizza", "https://images.unsplash.com/photo-1604382354936-07c5d9983bd3?w=500&q=80");
        IMAGE_MAP.put("burger", "https://images.unsplash.com/photo-1571091718767-18b5b1457add?w=500&q=80");
        IMAGE_MAP.put("sushi", "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?w=500&q=80");
        IMAGE_MAP.put("asian", "https://images.unsplash.com/photo-1552566626-52f8b828add9?w=500&q=80");
        IMAGE_MAP.put("indian", "https://images.unsplash.com/photo-1585937421612-70a008356f36?w=500&q=80");
        IMAGE_MAP.put("curry", "https://images.unsplash.com/photo-1603894584373-5ac82b2ae398?w=500&q=80");
        IMAGE_MAP.put("mexican", "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?w=500&q=80");
        IMAGE_MAP.put("taco", "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?w=500&q=80");
        IMAGE_MAP.put("dessert", "https://images.unsplash.com/photo-1551024601-564d6dbf4302?w=500&q=80");
        IMAGE_MAP.put("cake", "https://images.unsplash.com/photo-1578985545062-69928b1d9587?w=500&q=80");
        IMAGE_MAP.put("coffee", "https://images.unsplash.com/photo-1509042239860-f550ce710b93?w=500&q=80");
        IMAGE_MAP.put("chicken", "https://images.unsplash.com/photo-1626645738196-c2a7c87a8f58?w=500&q=80");
        IMAGE_MAP.put("biryani", "https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=500&q=80");
        IMAGE_MAP.put("pasta", "https://images.unsplash.com/photo-1645112411341-6c4fd023714a?w=500&q=80");
        IMAGE_MAP.put("healthy", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?w=500&q=80");
        IMAGE_MAP.put("salad", "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?w=500&q=80");
        IMAGE_MAP.put("default", "https://images.unsplash.com/photo-1504674900247-0877df9cc836?q=80&w=1000&auto=format&fit=crop");
    }

    public static void main(String[] args) {
        updateMissingImages();
    }

    public static void updateMissingImages() {
        System.out.println("🖼️ Starting Image Backfill...");

        try (Connection conn = DBConnection.getConnection()) {
            if (conn == null) {
                System.out.println("❌ Database connection failed.");
                return;
            }

            // 1. Update Restaurants
            updateTable(conn, "restaurants", "id", "name");

            // 2. Update Menu
            updateTable(conn, "menu", "id", "name");

            System.out.println("✅ Image Backfill Completed!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateTable(Connection conn, String tableName, String idCol, String nameCol) throws Exception {
        System.out.println("Processing table: " + tableName);

        // Select lines where image_path is NULL or empty
        String selectSQL = "SELECT " + idCol + ", " + nameCol + " FROM " + tableName +
                           " WHERE image_path IS NULL OR image_path = ''";

        try (PreparedStatement ps = conn.prepareStatement(selectSQL);
             ResultSet rs = ps.executeQuery()) {

            int count = 0;
            while (rs.next()) {
                int id = rs.getInt(idCol);
                String name = rs.getString(nameCol).toLowerCase();
                String imageUrl = getSmartImage(name);

                updateImage(conn, tableName, idCol, id, imageUrl);
                System.out.println("Updated " + tableName + " ID: " + id + " (" + rs.getString(nameCol) + ") -> " + imageUrl);
                count++;
            }

            if (count == 0) {
                System.out.println("No items found missing images in " + tableName);
            } else {
                System.out.println("Updated " + count + " items in " + tableName);
            }
        }
    }

    private static void updateImage(Connection conn, String tableName, String idCol, int id, String imageUrl) throws Exception {
        String updateSQL = "UPDATE " + tableName + " SET image_path = ? WHERE " + idCol + " = ?";
        try (PreparedStatement ps = conn.prepareStatement(updateSQL)) {
            ps.setString(1, imageUrl);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private static String getSmartImage(String name) {
        for (String key : IMAGE_MAP.keySet()) {
            if (name.contains(key)) {
                return IMAGE_MAP.get(key);
            }
        }
        return IMAGE_MAP.get("default");
    }
}
