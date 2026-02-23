package com.food.servlet;

import java.io.IOException;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/addMenu")
public class AddMenuServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        Menu m = new Menu();

        m.setRestaurantId(Integer.parseInt(req.getParameter("restaurantId")));
        m.setName(req.getParameter("name"));
        m.setDescription(req.getParameter("description"));
        m.setPrice(Double.parseDouble(req.getParameter("price")));
        m.setImagePath(req.getParameter("imagePath"));

        MenuDAO dao = new MenuDAOImpl();
        dao.insert(m);

        res.sendRedirect("admin-dashboard.jsp");
    }
}
