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

/*
*@author Ramya
*The web application HTTP request reads data,process and send HTTP response to the client. 
*validates the form data using regular expressions for different fields name,gender,date of birth,phone number,password.
*
*/

public class CreateCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        //retrieves the value of the object getParameter and store it in local variable.
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //Regular expression pattern to validate customer information
        String nameRegEx = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String dobRegEx = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]"
                + "{1,1}[-][\\d]{1,2}[-][\\d]{1,2}|^(2010)[\\d]{0,0}[-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegEx = "^[+0]\\d{1,2}\\d{6,11}$";
        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";
        
        boolean checkerror = true;
        String error = "";

        //These line of code checks the value stored in the variable matches the Regex pattern.
        //checks error message and store it in the session.
        if (!name.matches(nameRegEx)) {
            session.setAttribute("nameError", "Incorrect name format");
            checkerror = false;
        }
        if (gender.isEmpty()) {
            session.setAttribute("genderError", "Please Select your gender");
            checkerror = false;
        }
        if (!phone.matches(phoneRegEx)) {
            session.setAttribute("phoneError", "Incorrect phone format");
            checkerror = false;
        }
        if (!email.matches(emailRegEx)) {
            session.setAttribute("emailError", "Incorrect email format");
            checkerror = false;

        }
        if (!dob.matches(dobRegEx)) {
            session.setAttribute("dobError", "Incorrect  dob");
            checkerror = false;
        }
        if (!password.matches(passRegEx)) {
            session.setAttribute("passError", "Incorrect password format");
            checkerror = false;
        }

        //check if boolean value is true,if true retrieves object from the session and retrieves customer object with email from database.
        //if customer object is not null, it means customer already exists and error message is set in the session.
        //if the customer object is null, it means customer not exists and new customer is created and stored in the session.
        if (checkerror == true) {
            try {
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                Customer customerSql = customerSqlDAO.getCustomer(email);

                if (customerSql != null) {
                    error = "Customer already exists";
                    session.setAttribute("Error",error );              
                    request.getRequestDispatcher("createCustomer.jsp").include(request, response);
                } else {
                    customerSqlDAO.create(name, gender, dob, phone, email, password);
                    Customer customer = customerSqlDAO.getCustomer(email);
                    session.setAttribute("customer", customer);
                    request.getRequestDispatcher("customer.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            request.getRequestDispatcher("createCustomer.jsp").include(request, response);
        }
    }

}
