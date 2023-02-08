package com.controller;

import com.model.Admin;
import javax.servlet.http.HttpServlet;

import com.model.Admin;
import com.model.Admin;
import com.model.dao.AdminSqlDAO;
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
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
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
        String genderError = "";

         int errorNum = 0;
        //"^[\\d]{1,2}[-][\\d]{1,2}[-](19)[\\d]{2,2}$|^[\\d]{1,2}[-][\\d]{1,2}[-](200)[\\d]{1,1}$|^[\\d]{1,2}[-][\\d]{1,2}[-](2010)$";
        String nameRegex = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String dobRgex = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]{1,1}[-][\\d]{1,2}[-][\\d]{1,2}$|^(201)[\\d 0][-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegex = "^[+0]\\d{1,2}\\d{6,11}$";
        //String genderRegEx = "^M(ale)?$|^F(emale)?$";
        String passRegEx = "[A-Z][A-Za-z1-9!@#$%^&*]{8,}";
        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";

        if (!name.matches(nameRegex)) {
           nameError = "*Error: The name must be alphabetical!";
            errorNum++;

        }
        if (!email.matches(emailRegEx)) {
            emailError = "*Error: Invalid Email Address.";
            errorNum++;
        }
        if (!password.matches(passRegEx)) {
            passError = "*Error: Invalid Password!,minimum length:8,First letter:Capital";
            errorNum++;
        }
        if (!phone.matches(phoneRegex)) {
            phoneError = "*Error: Invalid Mobile Number.";
            errorNum++;
        }
        if (!dob.matches(dobRgex)) {
             dobError = "*Error: You've to be 13 or older to register";
            errorNum++;
        }
        if (gender.isEmpty()) {
           genderError = "*Error: Select: (F)emale|(M)ale";
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
            session.setAttribute("doberror", dobError);
            session.setAttribute("gendererror", genderError);
            request.getRequestDispatcher("register.jsp").include(request, response);

        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        }

    }
}
