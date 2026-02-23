package com.food.servlet;

import java.io.IOException;
import java.util.List;

import com.food.dao.MenuDAO;
import com.food.daoimpl.MenuDAOImpl;
import com.food.model.Menu;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    private MenuDAO menuDAO;

    @Override
    public void init() {
        menuDAO = new MenuDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1️⃣ Get restaurantId from URL
        int restaurantId = Integer.parseInt(request.getParameter("restaurantId"));

        // 2️⃣ Fetch menu items
        List<Menu> menuList = menuDAO.fetchByRestaurant(restaurantId);

        // 3️⃣ Send data to JSP
        request.setAttribute("menuList", menuList);
        request.setAttribute("restaurantId", restaurantId);

        // 4️⃣ Forward
        RequestDispatcher rd = request.getRequestDispatcher("menu.jsp");
        rd.forward(request, response);
    }
}
