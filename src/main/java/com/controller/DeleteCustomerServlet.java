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


public class DeleteCustomerServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = (String) session.getAttribute("emailView");

        Customer customer = null;
        if (emailView != null) {
            try {
                customer = customerSqlDAO.getCustomer(emailView);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            customer = (Customer) session.getAttribute("customer");
        }

        if (customer != null) {
            try {
                customerSqlDAO.delete(customer.getid());
            } catch (SQLException ex) {
                Logger.getLogger(DeleteCustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (emailView != null) {
            request.getRequestDispatcher("index.jsp").include(request, response);
        } else {
            session.invalidate();
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}
