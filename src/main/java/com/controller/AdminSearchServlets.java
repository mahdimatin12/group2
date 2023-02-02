
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


public class AdminSearchServlets extends HttpServlet {

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter(""+"id"));        
      
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");

        Customer customer = null;
        try {
            customer = customerSqlDAO.getCustomer(id);
        } catch (SQLException ex) {
            Logger.getLogger(AdminSearchServlets.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (customer != null) {
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("adminSearch.jsp").include(request, response);
        } else {
            session.setAttribute("error", "Customer does not exist");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
