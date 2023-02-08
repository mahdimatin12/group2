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
public class EditBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
        String movieName = request.getParameter("movie");
        int newMovieID = 0;
        try {
            newMovieID = bookingSqlDAO.getMovieID(movieName);
        } catch (SQLException ex) {
            Logger.getLogger(EditBookingServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        String bookingDate = request.getParameter("date");
        int movieID = Integer.parseInt(request.getParameter("mID"));
        int mbID = Integer.parseInt(request.getParameter("mbID"));
        int bookingID = Integer.parseInt(request.getParameter("bID"));

        String msg = null;
        if (mbID > 0) {
            try {
                bookingSqlDAO.update(bookingDate, newMovieID, mbID);
                msg = "Updated successfully";
                session.setAttribute("msg", msg);
                request.getRequestDispatcher("EditBooking.jsp").include(request, response);
            } catch (Exception e) {
                System.out.println("update failed");
            }
        }

    }
}
