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
*Java Servlet class that performs the operations of updating a customer information. 
*The web application HTTP request reads data,process and send HTTP response to the client. 
*validates the form data using regular expressions for different fields name,gender,date of birth,phone number,password.
*
*/
public class updateCustomerServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");
        
        //retrieves the value of the object getParameter and store it in local variable.
        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        //Regular expression pattern to validate customer information
        String nameRegEx = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String genderRegEx = "^M(ale)?$|^F(emale)?$|^m(ale)?$|^f(emale)?$";
        String dobRegEx = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]"
                + "{1,1}[-][\\d]{1,2}[-][\\d]{1,2}$|^(201)[\\d 0][-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegEx = "^[+0]\\d{1,2}\\d{6,11}$";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";
        

        Customer customer = null;
        
        //These line of code checks the value stored in the variable matches the Regex pattern.
        //checks error message and store it in the session.
        if (!name.matches(nameRegEx)) {
            session.setAttribute("nameError", "Failed! Name does not match");
        }
        if (!gender.matches(genderRegEx)) {
            session.setAttribute("genderError", "Failed! Select Male or Female");
        }

        if (!phone.matches(phoneRegEx)) {
            session.setAttribute("phoneError", "Failed! Enter a valid phone number");
        }
        if (!dob.matches(dobRegEx)) {
            session.setAttribute("dobError", "Failed! Date of birth must be at least 13 or older");
        }

        if (!password.matches(passRegEx)) {
            session.setAttribute("passError", "Failed! Incorrect password");
        }
        
       //Checks if the customer information has been submitted or not.
       //after the submission retrieves value from the session and updates customer information.
       //if the customer information not submitted it removes the session attribute update.

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

