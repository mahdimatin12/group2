/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 * @author Zaki|236370 Java Servlet code implements the logic to delete an admin
 * from the database. GET request is received by this servlet, it retrieves the
 * current session and the admin data access object (AdminSqlDAO) stored in the
 * session
 *
 */
public class DeleteCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");

        //adminSqlDAO object to retrieve the admini from the database using the getAdmin method and passing the email address.
        Customer customer = null;

        customer = (Customer) session.getAttribute("customer");

        // if the admin variable is not null, the delete method of the adminSqlDAO object is called to delete the administrator from the database.
        if (customer != null) {
            try {
                customerSqlDAO.delete(customer.getid());
                session.invalidate();
                request.getRequestDispatcher("index.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
