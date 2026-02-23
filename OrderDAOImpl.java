package com.food.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.food.dao.OrderDAO;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.util.DBConnection;

public class OrderDAOImpl implements OrderDAO {

    private static final String INSERT_ORDER =
            "INSERT INTO orders(user_id, restaurant_id, total_amount, status, payment_mode) VALUES(?,?,?,?,?)";

    private static final String INSERT_ORDER_ITEM =
            "INSERT INTO order_items(order_id, menu_id, quantity, item_total) VALUES(?,?,?,?)";

    @Override
    public int insert(Order order, Map<Integer, CartItem> cartItems) {

        int generatedOrderId = 0;

        try {
            Connection con = DBConnection.getConnection();

            // Start Transaction
            con.setAutoCommit(false);

            PreparedStatement orderPs =
                    con.prepareStatement(INSERT_ORDER, PreparedStatement.RETURN_GENERATED_KEYS);

            orderPs.setInt(1, order.getUserId());
            orderPs.setInt(2, order.getRestaurantId());
            orderPs.setDouble(3, order.getTotalAmount());
            orderPs.setString(4, order.getStatus());
            orderPs.setString(5, order.getPaymentMode());

            orderPs.executeUpdate();

            // Get generated order_id
            ResultSet rs = orderPs.getGeneratedKeys();

            if (rs.next()) {
                generatedOrderId = rs.getInt(1);
            }

            // Insert cart items
            PreparedStatement itemPs = con.prepareStatement(INSERT_ORDER_ITEM);

            for (CartItem item : cartItems.values()) {

                itemPs.setInt(1, generatedOrderId);
                itemPs.setInt(2, item.getItemId());
                itemPs.setInt(3, item.getQuantity());
                itemPs.setDouble(4, item.getSubTotal());

                itemPs.addBatch();
            }

            itemPs.executeBatch();

            // Commit Transaction
            con.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return generatedOrderId;
    }
}
