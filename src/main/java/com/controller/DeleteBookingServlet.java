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
public class DeleteBookingServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
        int mbID = Integer.parseInt(request.getParameter("mbID"));
        String msg = null;

        if (mbID > 0) {
            try {
                bookingSqlDAO.delete(mbID);
                msg = "your booking was successfully deleted";
                session.setAttribute("msg", msg);
                request.getRequestDispatcher("DeleteBooking.jsp").include(request, response);
            } catch (Exception e) {
                System.out.println("update failed");
            }
        } else {
            System.out.println("failed to git mbID");

        }
    }
}
