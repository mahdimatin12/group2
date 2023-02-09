/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Customer;
import com.model.dao.BookingSqlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
 * @author 236347
 */
public class Step1Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
        Customer customer = (Customer) session.getAttribute("customer");
        int id = customer.getid();
        String date = request.getParameter("date");

        if (date != null) {

            try {
                bookingSqlDAO.create(id, date);
                session.setAttribute("customer", customer);
                request.getRequestDispatcher("/Step2Servlet").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            request.getRequestDispatcher("booking.jsp").include(request, response);
        }
    }
}