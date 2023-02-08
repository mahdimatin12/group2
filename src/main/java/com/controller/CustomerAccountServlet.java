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
 *
 * @author 236370
 */
public class CustomerAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");
        String submitted = request.getParameter("submitted");
        Customer customer = null;

        if (submitted != null && submitted.equals("submitted")) {
            try {
                int ID = Integer.parseInt(request.getParameter("ID"));
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                String dob = String.valueOf(request.getParameter("dob"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                emailView = (String) session.getAttribute("emailView");

                if (emailView != null) {
                    customer = customerSqlDAO.getCustomer(emailView);
                } else {
                    customer = (Customer) session.getAttribute("customer");
                }
                customer.update(ID, name, gender, dob, phone, email, password);
                customerSqlDAO.update(name, gender, dob, phone, password, ID);
                              
                session.setAttribute("customer", customer);

            } catch (SQLException ex) {
                Logger.getLogger(CustomerAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        session.setAttribute("customer", customer);
        request.getRequestDispatcher("customeraccount.jsp").include(request, response);
    }

}
