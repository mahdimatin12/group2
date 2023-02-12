package com.controller;

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
It does the following:

Get the BookingSqlDAO object stored in the session.
Get the booking ID stored in the session.
Get the name of the movie selected by the user from the form.
Use the BookingSqlDAO object to retrieve the movie ID for the selected movie name from the database.

Store a success message in the session as an attribute.
Forward the request to the step2.jsp page for display.

*/
public class Step3Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
        int bID = (Integer) session.getAttribute("bID");

        String movieName = request.getParameter("movie").trim();
        String message = movieName + " added";
        int movieid = 0;

        try {
            movieid = bookingSqlDAO.getMovieID(movieName);
        } catch (SQLException ex) {
            Logger.getLogger(Step3Servlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (movieid > 0) {
            try {
                bookingSqlDAO.addMovieBooking(movieid, bID);
                session.setAttribute("added", message);
                request.getRequestDispatcher("step2.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(Step3Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            System.err.println("failed");
        }

    }
}
