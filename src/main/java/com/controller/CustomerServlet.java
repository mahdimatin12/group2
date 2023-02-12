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
 *Java Servlet that handles the login process for an Customer.
 */
public class CustomerServlet extends HttpServlet {

    //servlet implements the doPost method, which is called when the form on the login page is submitted.
    //doPost method, the servlet retrieves the email and password values from the request parameters.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");

        Customer customer = null;
        try {
            customer = customerSqlDAO.login(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(CustomerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        //If the Customer object is not found, the servlet sets an error message in the session and forwards the request to the login page.
        if (customer != null) {
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("main.jsp").include(request, response);
        // //If the Customer object is found, the servlet sets the Customer attribute in the session and forwards the request to the main page.
        } else {
            session.setAttribute("error", "Customer not found, try again.");
            request.getRequestDispatcher("login.jsp").include(request, response);
        }
    }
}
