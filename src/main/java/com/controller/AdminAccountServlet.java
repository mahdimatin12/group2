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
 * @author 236370
 */
public class AdminAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String emailView = request.getParameter("emailView");
        String submitted = request.getParameter("submitted");
        Admin admin = null;

        if (submitted != null && submitted.equals("submitted")) {
            try {
                int ID = Integer.parseInt(request.getParameter("ID"));
                String name = request.getParameter("name");
                String gender = request.getParameter("gender");
                Date dob = Date.valueOf(request.getParameter("dob"));
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                emailView = (String) session.getAttribute("emailView");

                if (emailView != null) {
                    admin = adminSqlDAO.getAdmin(emailView);
                } else {
                    admin = (Admin) session.getAttribute("admin");
                }
                admin.update(ID, name, gender, dob, phone, email, password);
                adminSqlDAO.update(name, gender, dob, phone, password, ID);
                              
                session.setAttribute("admin", admin);

            } catch (SQLException ex) {
                Logger.getLogger(AdminAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        session.setAttribute("admin", admin);
        request.getRequestDispatcher("adminaccount.jsp").include(request, response);
    }

}
