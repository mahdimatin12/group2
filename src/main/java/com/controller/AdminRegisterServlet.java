package com.controller;

import com.model.Admin;
import javax.servlet.http.HttpServlet;

import com.model.Admin;
import com.model.Admin;
import com.model.dao.AdminSqlDAO;
import com.model.dao.AdminSqlDAO;
import java.io.IOException;
import java.sql.Date;
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
        String gender = request.getParameter("gender");
        Date dob = Date.valueOf(request.getParameter("dob"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nextPage = false;

        String error = "";
        String nameError = "";
        String emailError = "";
        String passError = "";
        String dobError = "";
        String phoneError = "";

        int errorNum = 0;

        //String startdate = "1900-01-01";
        // String enddate = "2015-01-01";
        String nameRegex = "[a-z A-Z]+([ '-][a-zA-Z]+)*";

        String phoneRegex = "^[+0]\\d{1,2}\\d{6,11}$";
        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";

        if (!name.matches(nameRegex)) {
            nameError = "Incorrec name";
            errorNum++;
        }
        if (!email.matches(emailRegEx)) {
            emailError = "Incorrect email";
            errorNum++;
        }
        if (!password.matches(passRegEx)) {
            passError = "Incorrect password";
            errorNum++;
        }
        if (!phone.matches(phoneRegex)) {
            phoneError = "Incorrect phone";
            errorNum++;
        }
        if (errorNum == 0) {
            try {
                AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
                Admin adminSql = adminSqlDAO.getAdmin(email);
                if (adminSql != null) {
                    error = "Admin already exists";
                    session.setAttribute("error", error);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                } else {
                    nextPage = true;
                    adminSqlDAO.create(name, gender, dob, phone, email, password);
                    Admin admin = adminSqlDAO.getAdmin(email);

                    session.setAttribute("admin", admin);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AdminRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            session.setAttribute("nameerror", nameError);
            session.setAttribute("emailerror", emailError);
            session.setAttribute("passerror", passError);
            session.setAttribute("phoneerror", phoneError);
            request.getRequestDispatcher("register.jsp").include(request, response);

        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        }

    }
}
