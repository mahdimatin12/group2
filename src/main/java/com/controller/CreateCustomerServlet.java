package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateCustomerServlet extends HttpServlet {

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
        
        String nameRegEx ="[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String phoneRegEx= "^[+0]\\d{1,2}\\d{6,11}$";
        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";
        String passRegEx = "[A-Z][A-Za-z]{5,}\\d{2,}";
        
             if (!email.matches(emailRegEx) || !password.matches(passRegEx)){
            if (!name.matches(nameRegEx)) {
                session.setAttribute("nameError", "Incorrect name format");
                
            }if (!phone.matches(phoneRegEx)) {
                session.setAttribute("phoneError", "Incorrect phone format");
            }
            if (!email.matches(emailRegEx)) {
                session.setAttribute("emailError", "Incorrect email format");
            }
            if (!password.matches(passRegEx)) {
                session.setAttribute("passError", "Incorrect password format");
            }
            request.getRequestDispatcher("createCustomer.jsp").include(request, response);

       } else {
            try {
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                Customer customerSql = customerSqlDAO.getCustomer(email);

                if (customerSql != null) {
                    session.setAttribute("error", "Customer already exists");
                    request.getRequestDispatcher("createCustomer.jsp").include(request, response);
                } else {
                    customerSqlDAO.create(name, gender, dob, phone, email, password);
                    Customer customer = customerSqlDAO.getCustomer(email);
                    session.setAttribute("customer", customer);
                    request.getRequestDispatcher("main.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CreateCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
    