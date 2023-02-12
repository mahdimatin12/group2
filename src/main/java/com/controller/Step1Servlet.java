package com.controller;

import com.model.Customer;
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
This method is responsible for handling the logic when a user submits a form to pick a date.
The method checks if the selected date is not empty.
If it's not, it parses the selected date and the current date and compares them.
If the selected date is in the past, it sets an error message in the session and forwards the request to the same page.
*/
public class Step1Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String inputDate = request.getParameter("date");
        String empty = "";

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currentDate = Calendar.getInstance();
        String systemDate = formatter.format(currentDate.getTime());

        if (!inputDate.isEmpty()) {

            try {
                Date convertedinputDate = formatter.parse(inputDate);
                Date convertedsystemDate = formatter.parse(systemDate);

                if (convertedinputDate.before(convertedsystemDate)) {
                    empty = "Date cannot be in the past";
                    session.setAttribute("empty", empty);
                    request.getRequestDispatcher("step1.jsp").include(request, response);

                } else {
                    //Finally, the method forwards the request to the Step2Servlet to continue the booking process:
                    BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
                    Customer customer = (Customer) session.getAttribute("customer");
                    int id = customer.getid();
                    try {
                        bookingSqlDAO.create(id, inputDate);
                    } catch (SQLException ex) {
                        Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    session.setAttribute("customer", customer);
                    request.getRequestDispatcher("Step2Servlet").include(request, response);
                }
            } catch (ParseException ex) {
                Logger.getLogger(Step1Servlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            empty = "You should pick a date";
            session.setAttribute("empty", empty);
            request.getRequestDispatcher("step1.jsp").include(request, response);

        }

    }
}
