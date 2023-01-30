//package com.controller;
//
//import com.model.Booking;
//import com.model.Customer;
//import com.model.dao.BookingSqlDAO;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//
//public class BookingServlet extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        BookingSqlDAO BookingSqlDAO = (BookingSqlDAO) session.getAttribute("BookingSqlDAO");
//        Customer user = (Customer) session.getAttribute("customer");
//        //String text = request.getParameter("booking");
//        if (text != null && !text.isEmpty()) {
//            try {
//                BookingSqlDAO.create(user.getID(), text,"mydb.blogs");
//                session.setAttribute("user", user);
//            } catch (SQLException ex) {
//                Logger.getLogger(BlogServlet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }        
//        request.getRequestDispatcher("main.jsp").include(request, response);
//    }    
//}
