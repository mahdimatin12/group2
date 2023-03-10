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
import java.io.PrintWriter;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
public class MoviePageStep1Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String inputDate = request.getParameter("date");
        String empty = "";
        
        Movie movie = (Movie)session.getAttribute("movie");
        int movieid = movie.getid();
        String msg2 = "";
        
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        String systemDate = formatter.format(currentDate.getTime());

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
                        int bookingid = bookingSqlDAO.currentBooking();
                        bookingSqlDAO.addMovieBooking(movieid, bookingid);
                    } catch (SQLException ex) {
                        Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    session.setAttribute("customer", customer);
                    msg2 = "your movie was added to your booking";
                    session.setAttribute("msg2", msg2);
                    request.getRequestDispatcher("booking.jsp").include(request, response);
                }
            } catch (ParseException ex) {
                Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            empty = "you should pick a date";
            session.setAttribute("empty", empty);
            request.getRequestDispatcher("moviebookingpage.jsp").include(request, response);

        }

    }
}
