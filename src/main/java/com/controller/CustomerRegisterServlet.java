package com.controller;

import com.model.Customer;
import javax.servlet.http.HttpServlet;

import com.model.Customer;
import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CustomerRegisterServlet extends HttpServlet {

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
        String dobRgex = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]{1,1}[-][\\d]{1,2}[-][\\d]{1,2}|^(2010)[\\d]{0,0}[-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
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
            phoneError = "*Error: Enter a valid number";
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
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                Customer customerSql = customerSqlDAO.getCustomer(email);
                if (customerSql != null) {
                    error = "Customer already exists";
                    session.setAttribute("error", error);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                } else {
                    nextPage = true;
                    customerSqlDAO.create(name, gender, dob, phone, email, password);
                    Customer customer = customerSqlDAO.getCustomer(email);

                    session.setAttribute("customer", customer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {

            session.setAttribute("nameerror2", nameError);
            session.setAttribute("emailerror2", emailError);
            session.setAttribute("passerror2", passError);
            session.setAttribute("phoneerror2", phoneError);
            session.setAttribute("doberror2", dobError);
            session.setAttribute("gendererror2", genderError);
            request.getRequestDispatcher("register.jsp").include(request, response);

        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        }

    }
}
