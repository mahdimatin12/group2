package com.controller;

import com.model.dao.BookingSqlDAO;
import java.io.IOException;
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
In the doPost method, the HttpServletRequest object is used to retrieve the value of the 
"mbID" parameter from the form.
The mbID is then used to delete a booking record from the database using the delete method of the BookingSqlDAO class.

BookingSqlDAO object from the session is used to interact with the database to delete the booking record.
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
                request.getRequestDispatcher("booking.jsp").include(request, response);
            } catch (Exception e) {
                System.out.println("update failed");
            }
        } else {
            System.out.println("failed to get mbID");

        }
    }
}
