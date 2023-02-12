
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
*Java Servlet class that performs the operations for searching a customer in a database by ID.
*The web application HTTP request reads data,process and send HTTP response to the client.
*/
public class AdminSearchServlet extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
        //retrieves current HTTP session and retrieves customer ID to search from request parameters and parse it in to an integer.
        //if parsing fails sets an error message.
        //if parsing successfull retrieves 'customerSqlDAO' from the session passing the customer ID as parameter and retrieve the customer information.
        //if the customer found sets customer information in session.
        //if the customer not found sets an error message in the session.
        HttpSession session = request.getSession();
        int id = 0;
        
        try{
           id = Integer.parseInt(request.getParameter(""+"id"));
        }catch(NumberFormatException e) {
            Logger.getLogger(AdminSearchServlet.class.getName()).log(Level.SEVERE, null,e);
        
            session.setAttribute("error", "!Invalid input");
            request.getRequestDispatcher("customer.jsp").include(request, response);
        }
         
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        Customer customer = null;
        try {            
            customer = customerSqlDAO.getCustomer(id);
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
                                 
        }
        if (customer != null) {
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("adminSearch.jsp").include(request, response);
        } else {
            session.setAttribute("error", " ID Not Found!");
            request.getRequestDispatcher("customer.jsp").include(request, response);
        }
    }
}
