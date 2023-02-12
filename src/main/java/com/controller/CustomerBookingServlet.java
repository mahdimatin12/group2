/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Customer;
import com.model.Movie;
import com.model.dao.BookingSqlDAO;
import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
CustomerBookingServlet is invoked when a customer submits a form on the website to make a booking.
The servlet retrieves the input date and movie information from the session and the form.
It checks if the input date is empty and if it is,
it sets an error message in the session and forwards the request to the step1.
jsp page display the error message. 

 */
public class CustomerBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String inputDate = request.getParameter("date");
        String empty = "";
        String confirmationmsg = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        String systemDate = formatter.format(currentDate.getTime());
        Movie movie = (Movie) session.getAttribute("movie");
        int moviesid = movie.getid();

        /*
        If the input date is not empty and not in the past,
        the servlet creates a booking in the database using the BookingSqlDAO object,
        retrieves the booking ID, and associates the movie with the booking by using the addMovieBooking method of the BookingSqlDAO object.
        
         */
        if (!inputDate.isEmpty()) {

            try {
                Date convertedinputDate = formatter.parse(inputDate);
                Date convertedsystemDate = formatter.parse(systemDate);

                if (convertedinputDate.before(convertedsystemDate)) {
                    empty = "date cannot be in the past";
                    session.setAttribute("empty", empty);
                    request.getRequestDispatcher("step1.jsp").include(request, response);

                } else {
                    BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
                    Customer customer = (Customer) session.getAttribute("customer");
                    int id = customer.getid();
                    try {
                        bookingSqlDAO.create(id, inputDate);
                    } catch (SQLException ex) {
                        Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //Finally, it sets a confirmation message in the session and forwards the request to the booking.jsp page to display the confirmation message.
                    try {
                        int bookingid = (Integer) bookingSqlDAO.currentBooking();
                        if (bookingid < 0) {
                            bookingSqlDAO.addMovieBooking(moviesid, bookingid);
                            confirmationmsg = "your booking was successful";
                            session.setAttribute("confirmationmsg", confirmationmsg);
                            request.getRequestDispatcher("booking.jsp").include(request, response);

                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CustomerBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (ParseException ex) {
                Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            empty = "you should pick a date";
            session.setAttribute("empty", empty);
            request.getRequestDispatcher("step1.jsp").include(request, response);

        }

    }
}
