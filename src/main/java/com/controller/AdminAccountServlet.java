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
*@Author: Zaki|236370
*Java Servlet class which handles the functionality of updating an admin account information. I
*java web application which receives HTTP requests and sends HTTP responses.
*validates the input data and checks if the input data for the name, password, phone number, date of birth.
*
 */
public class AdminAccountServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
        String emailView = request.getParameter("emailView");

        Admin admin = null;
        boolean nextPage = false;

        String nameError = "";
        String emailError = "";
        String passError = "";
        String dobError = "";
        String phoneError = "";
        String genderError = "";

        int errorNum = 0;

        //Regex for validation criteria.
        String nameRegex = "[a-z A-Z]+([ '-][a-zA-Z]+)*";
        String dobRgex = "^(19)[\\d]{2,2}[-][\\d]{1,2}[-][\\d]{1,2}$|^(200)[\\d]{1,1}[-][\\d]{1,2}[-][\\d]{1,2}|^(2010)[\\d]{0,0}[-][\\d]{1,2}[-][\\d]{1,2}$";//1900-2010
        String phoneRegex = "^[+0]\\d{1,2}\\d{6,11}$";
        String genderRegEx = "^M(ale)?$|^F(emale)?$|^m(ale)?$|^f(emale)?$";
        String passRegEx = "[A-Z][A-Za-z1-9!@#$%^&*]{8,}";

        //retrieves the data entered in the form using the request object's getParameter
        int ID = Integer.parseInt(request.getParameter("ID"));
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        //All below if are Checking regex with the entered data by user
        //error messages for invalid input data such as invalid name, password, phone number, date of birth, and gender.
        if (!name.matches(nameRegex)) {
            nameError = "*Update Failed: The name must be alphabetical!";
            errorNum++;
        }

        if (!password.matches(passRegEx)) {
            passError = "*Update Faild :Invalid Password!,minimum length:8,First letter:Capital";
            errorNum++;
        }
        if (!phone.matches(phoneRegex)) {
            phoneError = "*Update Faild: Enter a valid number";
            errorNum++;
        }
        if (!dob.matches(dobRgex)) {
            dobError = "*Update Faild: You've to be 13 or older to register";
            errorNum++;
        }
        if (!gender.matches(genderRegEx)) {
            genderError = "*Update Faild: Select: (F)emale|(M)ale";
            errorNum++;
        }

        // If all the input data is valid, it updates the admin account by calling 
        //...the update method of the Admin class and the update method of the AdminSqlDAO class.
        if (errorNum == 0) {
            String submitted = request.getParameter("submitted");
            if (submitted != null && submitted.equals("submitted")) {

                try {
                    emailView = (String) session.getAttribute("emailView");
                    if (emailView != null) {
                        admin = adminSqlDAO.getAdmin(emailView);
                    } else {
                        admin = (Admin) session.getAttribute("admin");
                    }
                    admin.update(ID, name, gender, dob, phone, email, password);
                    adminSqlDAO.update(name, gender, dob, phone, password, ID);
                    nextPage = true;
                    session.setAttribute("admin", admin);
                    session.setAttribute("updatemsg", "update Successful!");

                    session.removeAttribute("nameerror");
                    session.removeAttribute("passerror");
                    session.removeAttribute("phoneerror");
                    session.removeAttribute("doberror");
                    session.removeAttribute("gendererror");

                } catch (SQLException ex) {
                    Logger.getLogger(AdminAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } else {

            // any error in the input data, the error message is stored in the session and passed back to the view. 
            session.setAttribute("nameerror", nameError);
            session.setAttribute("emailerror", emailError);
            session.setAttribute("passerror", passError);
            session.setAttribute("phoneerror", phoneError);
            session.setAttribute("doberror", dobError);
            session.setAttribute("gendererror", genderError);
            session.removeAttribute("updatemsg");
            request.getRequestDispatcher("usersaccount.jsp").include(request, response);

        }

        if (nextPage) {
            session.setAttribute("admin", admin);
            request.getRequestDispatcher("usersaccount.jsp").include(request, response);
        }
    }
}
