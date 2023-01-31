/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class DeleteAdminServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String emailView = (String) session.getAttribute("emailView");

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
