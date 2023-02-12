
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

/**
 *@author Zaki|236370
 *Java Servlet code implements the logic to delete an admin from the database.
 *GET request is received by this servlet, it retrieves the current session and the admin data access object (AdminSqlDAO) stored in the session
 * 
 */
public class DeleteAdminServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String emailView = (String) session.getAttribute("emailView");
      
        
        //adminSqlDAO object to retrieve the admini from the database using the getAdmin method and passing the email address.
        Admin admin = null;
        if (emailView != null) {
            try {
                admin = adminSqlDAO.getAdmin(emailView);
            } catch (SQLException ex) {
                Logger.getLogger(DeleteAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            admin = (Admin) session.getAttribute("admin");
        }
       // if the admin variable is not null, the delete method of the adminSqlDAO object is called to delete the administrator from the database.
        if (admin != null) {
            try {
                adminSqlDAO.delete(admin.getid());
            } catch (SQLException ex) {
                Logger.getLogger(DeleteAdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (emailView != null) {
            request.getRequestDispatcher("index.jsp").include(request, response);
        } else {
            session.invalidate();
            request.getRequestDispatcher("index.jsp").include(request, response);
        }
    }
}
