
package com.controller;

import com.model.Admin;
import com.model.dao.AdminSqlDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
 * @author 236370
 */
public class AdminMainServlet extends HttpServlet {

     @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String emailView = request.getParameter("emailView");

        Admin admin = null;
        if (emailView != null) {
            try {
                admin = adminSqlDAO.getAdmin(emailView);
                session.setAttribute("emailView", emailView);
            } catch (SQLException ex) {
                Logger.getLogger(AdminMainServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            admin = (Admin) session.getAttribute("admin");
        }
        session.setAttribute("admin", admin);
        request.getRequestDispatcher("adminaccount.jsp").include(request, response);
    }
}
