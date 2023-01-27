package com.controller;

import com.model.Admin;
import javax.servlet.http.HttpServlet;

import com.model.Admin;
import com.model.dao.AdminSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nextPage = false;
        String error = "";

        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";

        if (!email.matches(emailRegEx) || !password.matches(passRegEx)) {
            error = "Incorrect ";
            if (!email.matches(emailRegEx)) {
                error += "email";
            }

            if (!password.matches(passRegEx)) {
                if (error.contains("email")) {
                    error += " and ";
                }
                error += "password";
            }
            error += " format";

        } else {

            try {
                AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
                Admin adminSql = adminSqlDAO.getAdmin(email);
                if (adminSql != null) {
                    error = "Admin already exists";
                } else {
                    nextPage = true;
                    adminSqlDAO.create(name, email, password);
                    Admin admin = adminSqlDAO.getAdmin(email);
                   
                  session.setAttribute("admin", admin);
                }
            } catch (SQLException ex) {
                Logger.getLogger(RegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        } else {
            session.setAttribute("error", error);
            request.getRequestDispatcher("register.jsp").include(request, response);
    
}
}
}
