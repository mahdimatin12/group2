package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class updateCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");

        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String nameRegEx = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String dobRegEx = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]"
                + "{1,1}[-][\\d]{1,2}[-][\\d]{1,2}$|^(201)[\\d 0][-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegEx = "^[+0]\\d{1,2}\\d{6,11}$";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";
        //String error = "";

        Customer customer = null;

        if (!name.matches(nameRegEx)) {
            session.setAttribute("nameError", "Incorrect name format");
        }
        if (gender.isEmpty()) {
            session.setAttribute("genderError", "Please Select your gender");
        }

        if (!phone.matches(phoneRegEx)) {
            session.setAttribute("phoneError", "Incorrect phone format");
        }
        if (!dob.matches(dobRegEx)) {
            session.setAttribute("dobError", "Incorrect dob format");
        }

        if (!password.matches(passRegEx)) {
            session.setAttribute("passError", "Incorrect password format");
        }

        String submitted = request.getParameter("submitted");
        if (submitted != null && submitted.equals("submitted")) {

            try {
                emailView = (String) session.getAttribute("emailView");

                if (emailView != null) {
                    customer = customerSqlDAO.getCustomer(emailView);
                } else {
                    customer = (Customer) session.getAttribute("customer");
                }
                customer.update(ID, name, gender, dob, phone, email, password);
                customerSqlDAO.update(name, gender, dob, phone, password, ID);
                session.setAttribute("customer", customer);
                session.setAttribute("update", "Update is Successful");
            
            } catch (SQLException ex) {
                Logger.getLogger(updateCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            session.removeAttribute("update");
        }
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("customerEmailView.jsp").include(request, response);
        }
    }

