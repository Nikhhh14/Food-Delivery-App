package com.food.servlet;

import java.io.IOException;
import java.util.Map;

import com.food.dao.OrderDAO;
import com.food.daoimpl.OrderDAOImpl;
import com.food.model.CartItem;
import com.food.model.Order;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {

    private OrderDAO orderDAO;

    @Override
    public void init() {
        orderDAO = new OrderDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ⭐ Get Logged In User
        User user = (User) session.getAttribute("loggedInUser");

        // ⭐ Get Cart
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        if (user == null || cart == null || cart.isEmpty()) {
            response.sendRedirect("login.jsp");
            return;
        }

        // ⭐ Calculate Total Amount
        double totalAmount = 0;
        int restaurantId = 0;

        for (CartItem item : cart.values()) {
            totalAmount += item.getSubTotal();
            restaurantId = item.getRestaurantId(); // assuming single restaurant
        }

        // ⭐ Get Payment Mode
        String paymentMode = request.getParameter("paymentMode");

        // ⭐ Create Order Object
        Order order = new Order();
        order.setUserId(user.getId());
        order.setRestaurantId(restaurantId);
        order.setTotalAmount(totalAmount);
        order.setStatus("Pending");
        order.setPaymentMode(paymentMode);

        // ⭐ Insert Order into DB
        int orderId = orderDAO.insert(order, cart);

        if (orderId > 0) {

            // Clear Cart
            session.removeAttribute("cart");

            response.sendRedirect("confirm.jsp?orderId=" + orderId);

        } else {
            response.getWriter().println("Order Failed");
        }
    }
}
