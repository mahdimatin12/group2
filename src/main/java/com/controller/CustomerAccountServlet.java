/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
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

public class CustomerAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");

        Customer customer = null;
        boolean nextPage = false;

        String nameError = "";
        String passError = "";
        String dobError = "";
        String phoneError = "";
        String genderError = "";

        int errorNum = 0;
        
        String nameRegex = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String dobRgex = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]{1,1}[-][\\d]{1,2}[-][\\d]{1,2}$|^(201)[\\d 0][-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegex = "^[+0]\\d{1,2}\\d{6,11}$";
        String genderRegEx = "^M(ale)?$|^F(emale)?$";
        String passRegEx = "[A-Z][A-Za-z1-9!@#$%^&*]{8,}";

        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (!name.matches(nameRegex)) {
            nameError = "*Update Failed: The name must be alphabetical!";
            errorNum++;

        }

        if (!password.matches(passRegEx)) {
            passError = "*Update Failed:Invalid Password!,minimum length:8,First letter:Capital";
            errorNum++;
        }
        if (!phone.matches(phoneRegex)) {
            phoneError = "*Update Failed: Enter a valid number";
            errorNum++;
        }
        if (!dob.matches(dobRgex)) {
            dobError = "*Update Failed: You've to be 13 or older to Update";
            errorNum++;
        }
        if (!gender.matches(genderRegEx)) {
            genderError = "*Update Failed: Select: (F)emale|(M)ale";
            errorNum++;
        }

        if (errorNum == 0) {
            String submitted = request.getParameter("submitted");
            if (submitted != null && submitted.equals("submitted")) {

                try {
                    emailView = (String) session.getAttribute("emailView");
                    if (emailView != null) {
                        customer = customerSqlDAO.getCustomer(emailView);
                    } else {
                        customer = (Customer) session.getAttribute("customer");
                    }
                    customer.update(ID, name, gender, dob, phone, email, password);
                    customerSqlDAO.update(name, gender, dob, phone, password, ID);
                    nextPage = true;
                    session.setAttribute("customer", customer);
                    
                    session.setAttribute("updatemsg", "update Successful!");

                    session.removeAttribute("nameerror");
                    session.removeAttribute("passerror");
                    session.removeAttribute("phoneerror");
                    session.removeAttribute("doberror");
                    session.removeAttribute("gendererror");

                } catch (SQLException ex) {
                    Logger.getLogger(CustomerAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else {

            session.setAttribute("nameerror", nameError);
            session.setAttribute("passerror", passError);
            session.setAttribute("phoneerror", phoneError);
            session.setAttribute("doberror", dobError);
            session.setAttribute("gendererror", genderError);
            session.removeAttribute("updatemsg");
            request.getRequestDispatcher("usersaccount.jsp").include(request, response);

        }

        if (nextPage) {
            session.setAttribute("customer", customer);
            request.getRequestDispatcher("usersaccount.jsp").include(request, response);
        }
    }
}
