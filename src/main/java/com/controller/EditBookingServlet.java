package com.controller;

import com.model.dao.BookingSqlDAO;
import java.io.IOException;
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
import java.util.Date;

/**
 *
 * @author 236347
 */

/*
The servlet does the following:

Retrieve the BookingSqlDAO from the session.

Retrieve the movie name and booking date from the request parameters.
Use the BookingSqlDAO to get the movie ID for the selected movie name.
Check if both the movie name and booking date are not empty.
Convert the booking date and the current system date to the Date format using a SimpleDateFormat.
Check if the converted booking date is not in the past.

If the booking date is not in the past, retrieve the mbID (member booking ID) from the request parameters,
and use the BookingSqlDAO to update the booking date and movie ID for the booking with the given mbID.
If the movie name or booking date is empty, set a corresponding error message in the session and forward the request to the EditBooking.jsp page.

*/
public class EditBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");

        String movieName = request.getParameter("movie");
        String bookingDate = request.getParameter("date");

        int newMovieID = 0;

        String msg1 = "";
        String msg2 = "";
        String msg3 = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        String systemDate = formatter.format(currentDate.getTime());

        if (!movieName.isEmpty()) {
            try {
                newMovieID = bookingSqlDAO.getMovieID(movieName);
                //int movieID = Integer.parseInt(request.getParameter("mID"));
                //int bookingID = Integer.parseInt(request.getParameter("bID"));

            } catch (SQLException ex) {
                Logger.getLogger(EditBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            String msg = null;

            if (!bookingDate.isEmpty()) {
                Date convertedinputDate = null;
                Date convertedsystemDate = null;
                try {
                    convertedinputDate = formatter.parse(bookingDate);
                    convertedsystemDate = formatter.parse(systemDate);
                } catch (ParseException ex) {
                    Logger.getLogger(EditBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!convertedinputDate.before(convertedsystemDate)) {
                    try {
                        int mbID = Integer.parseInt(request.getParameter("mbID"));
                        bookingSqlDAO.update(bookingDate, newMovieID, mbID);
                        msg = "Updated successfully";
                        session.setAttribute("msg", msg);
                        request.getRequestDispatcher("booking.jsp").include(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(EditBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    msg3 = "Date cannot be in the past";
                    session.setAttribute("msg3", msg3);
                    request.getRequestDispatcher("EditBooking.jsp").include(request, response);

                }

            } else {
                msg2 = "you should pick a date";
                session.setAttribute("msg2", msg2);
                request.getRequestDispatcher("EditBooking.jsp").include(request, response);
            }

            //if(!bookingDate.isEmpty):
        } else {
            msg1 = "you should pick a movie";
            session.setAttribute("msg1", msg1);
            request.getRequestDispatcher("EditBooking.jsp").include(request, response);
        }

    }
}
