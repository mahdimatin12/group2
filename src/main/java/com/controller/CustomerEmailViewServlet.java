package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
*@author Ramya
*Java Servlet class that performs the operations of viewing a customer.
*The web application HTTP request reads data,process and send HTTP response to the client.
*The java annotation that maps the servlet to the URL pattern "/ShowCustomerInfoServlet".
*/

@WebServlet("/ShowCustomerInfoServlet")
public class CustomerEmailViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //retrieves the object from the HTTP session using the key "customerSqlDAO".
        //retrieves the email of the customer to view from the request parameters.
        //check if the variable 'emailView' not null, if 'emeilView' not null retrieves the customer information from database
        //-and store the result in the variable customer.
        //if 'emailView' is null retrieve the customer information from the session and stores the result in the variable customer. 
        HttpSession session = request.getSession();

        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");

        Customer customer = null;
        if (emailView != null) {
            try {
                customer = customerSqlDAO.getCustomer(emailView);
                session.setAttribute("emailView", emailView);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerEmailViewServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            customer = (Customer) session.getAttribute("customer");
        }
        session.setAttribute("customer", customer);
        request.getRequestDispatcher("customerEmailView.jsp").include(request, response);
    }
}
