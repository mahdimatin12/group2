package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        CustomerSqlDAO userSqlDAO = (CustomerSqlDAO) session.getAttribute("userSqlDAO");

        Customer user = null;
        try {
            user = userSqlDAO.login(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (user != null) {
            session.setAttribute("user", user);
            request.getRequestDispatcher("blog.jsp").include(request, response);
        } else {
            session.setAttribute("error", "Customer does not exist");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
