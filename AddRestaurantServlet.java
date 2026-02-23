package com.food.servlet;

import java.io.IOException;

import com.food.dao.RestaurantDAO;
import com.food.daoimpl.RestaurantDAOImpl;
import com.food.model.Restaurant;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addRestaurant")
public class AddRestaurantServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Restaurant r = new Restaurant();

        r.setName(req.getParameter("name"));
        r.setCuisine(req.getParameter("cuisine"));
        r.setRating(Double.parseDouble(req.getParameter("rating")));
        r.setImagePath(req.getParameter("imagePath"));

        RestaurantDAO dao = new RestaurantDAOImpl();
        dao.insert(r);

        res.sendRedirect("admin-dashboard.jsp");
    }
}
