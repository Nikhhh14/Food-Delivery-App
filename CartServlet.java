package com.food.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.CartItem;
import com.food.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int menuId = Integer.parseInt(request.getParameter("menuId"));
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // ⭐ Fetch menu item from DB
        Menu menu = menuDAO.fetchById(menuId);

        // ⭐ Get Session
        HttpSession session = request.getSession();

        // ⭐ Get Cart from Session
        Map<Integer, CartItem> cart =
                (Map<Integer, CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new HashMap<>();
        }

        // ⭐ Check if item already exists
        if (cart.containsKey(menuId)) {

            CartItem existing = cart.get(menuId);
            existing.setQuantity(existing.getQuantity() + quantity);

            // Recalculate subtotal
            existing.setSubTotal(existing.getPrice() * existing.getQuantity());

        } else {

            CartItem item = new CartItem(
                    menu.getId(),
                    restaurantId,
                    menu.getName(),
                    menu.getPrice(),
                    quantity,
                    menu.getPrice() * quantity,
                    menu.getImagePath() // Added imagePath
            );

            cart.put(menuId, item);
        }

        // ⭐ Save cart back to session
        session.setAttribute("cart", cart);

        // Redirect to cart page
        response.sendRedirect("cart.jsp");
    }
}
