package com.controller;

import com.model.Customer;
import com.model.dao.BookingSqlDAO;
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
 * @author 236347
 */

/*
This servlet class handles the POST request from the step1.jsp page.
he servlet retrieves the current session and gets the BookingSqlDAO and Customer objects from the session.
Then, it calls the currentBooking method from the BookingSqlDAO class to get the current booking ID and stores it in the session.
Finally, it forwards the request to step2.jsp for further processing.

*/
public class Step2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
        Customer customer = (Customer) session.getAttribute("customer");           

        try {
            int bID = bookingSqlDAO.currentBooking();
            session.setAttribute("bID", bID);
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("step2.jsp").include(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
