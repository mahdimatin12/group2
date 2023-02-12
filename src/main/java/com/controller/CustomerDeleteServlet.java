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
*Java Servlet class that performs the operations of deleting a customer.
*The web application HTTP request reads data,process and send HTTP response to the client. 
*/
public class CustomerDeleteServlet extends HttpServlet {
    
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //retrieves the object from the HTTP session using the key "customerSqlDAO".
        //retrieves the email of the customer to be deleted from the session.
        //if the customer object is not null, the customer exists and it calls the "deletebookings" from customerSqlDAO class to 
        //delete all the bookings associated with the customer. 
        //Then it calls the "delete" method of the customerSqlDAO class to delete the customer.
        HttpSession session = request.getSession();
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        
        String emailView = (String) session.getAttribute("email");

        Customer customer = null;
        if (emailView != null) {
            try {
                customer = customerSqlDAO.getCustomer(emailView);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            customer = (Customer) session.getAttribute("customer");
        }
        if (customer != null) {
            try {
                customerSqlDAO.deletebookings(customer.getid());
                customerSqlDAO.delete(customer.getid());            
            } catch (SQLException ex) {
                Logger.getLogger(CustomerDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (emailView != null) {
            request.getRequestDispatcher("customer.jsp").include(request, response);
        } else {
            
            request.getRequestDispatcher("customer.jsp").include(request, response);
        }
    } 
}
