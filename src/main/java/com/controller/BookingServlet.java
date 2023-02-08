package com.controller;

import com.model.Booking;
import com.model.Bookings;
import com.model.Customer;
import com.model.dao.BookingSqlDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("BookingSqlDAO");
        Bookings bookings = null;
        Customer customer = (Customer) session.getAttribute("customer");
        int id = customer.getid();
        String movieName = request.getParameter("movies");
        Date date = Date.valueOf(request.getParameter("date"));
        if (movieName != null) {
            bookings.add(new Booking());
        }

        request.getRequestDispatcher("booking.jsp").include(request, response);
    }
}
