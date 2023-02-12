package com.controller;

import com.model.Admin;
import com.model.dao.AdminSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 *Java Servlet that handles the login process for an admin.
 */
public class AdminServlet extends HttpServlet {
    //servlet implements the doPost method, which is called when the form on the login page is submitted.
    //doPost method, the servlet retrieves the email and password values from the request parameters.

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Admin admin = null;

        if (email != null) {
            try {
                admin = adminSqlDAO.login(email, password);
            } catch (SQLException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            admin = (Admin) session.getAttribute("admin");
        }

        //If the Admin object is not found, the servlet sets an error message in the session and forwards the request to the login page.
        if (admin == null) {
            session.setAttribute("error", "Admin not found, Try again.");
            request.getRequestDispatcher("login.jsp").include(request, response);
            //If the Admin object is found, the servlet sets the admin attribute in the session and forwards the request to the main page.
        } else {
            session.setAttribute("admin", admin);
            request.getRequestDispatcher("main.jsp").include(request, response);
        }
    }
}
