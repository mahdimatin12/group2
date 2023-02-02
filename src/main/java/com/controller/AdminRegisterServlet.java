package com.controller;

import com.model.Admin;
import javax.servlet.http.HttpServlet;

import com.model.Admin;
import com.model.dao.AdminSqlDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
        String gender = request.getParameter("gender");
        Date dob= Date.valueOf( request.getParameter("dob"));
        String phone= request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nextPage = false;
        String error = "";
        
        
        String nameRegex="^[a-zA-Z\\\\s]+";
        String dobRegex="^\\d{4}-\\d{2}-\\d{2}$";
        String phoneRegex="^(\\+\\d{1,3}( )?)?((\\(\\d{1,3}\\))|\\d{1,3})[- .]?\\d{3,4}[- .]?\\d{4}$";
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
                    adminSqlDAO.create(name, gender, dob, phone, email, password);
                    Admin admin = adminSqlDAO.getAdmin(email);
                   
                  session.setAttribute("admin", admin);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        } else {
            session.setAttribute("errorRegister", error);
            request.getRequestDispatcher("register.jsp").include(request, response);
    
}
}
}
