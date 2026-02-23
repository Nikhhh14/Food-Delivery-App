package com.food.servlet;

import java.io.IOException;

import com.food.dao.UserDAO;
import com.food.daoimpl.UserDAOImpl;
import com.food.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ⭐ ADMIN LOGIN CHECK
        if(email.equals("admin@gmail.com") && password.equals("admin123")){
            HttpSession session = request.getSession();
            session.setAttribute("admin", "true");
            response.sendRedirect("admin-dashboard.jsp");
            return;
        }

        // ⭐ NORMAL USER LOGIN
        User user = userDAO.fetchByEmail(email);

        if (user != null && user.getPassword().equals(password)) {

            HttpSession session = request.getSession();
            session.setAttribute("loggedInUser", user);

            response.sendRedirect("home");

        } else {
            response.getWriter().println("Invalid Email or Password");
        }
    }
}
