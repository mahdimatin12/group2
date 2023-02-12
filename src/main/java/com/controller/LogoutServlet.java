package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author ZAKI|236370
 * this servlet class for logging out a user from a web application. 
 * servlet class extends HttpServlet
 * 
 */

public class LogoutServlet extends HttpServlet {
   //doGet method is overriden to handle HTTP GET requests.
    //When a user logs out, the method invalidates the current HTTP session using session.invalidate().
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("index.jsp").include(request, response);
    }   
}
