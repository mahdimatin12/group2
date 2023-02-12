package com.controller;

import javax.servlet.http.HttpServlet;
import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author Zaki|236370 
 * Java servlet code for handling the registration of an
 * "Customer" user in a web application. this class include e necessary libraries.
 * The servlet extends the HttpServlet class and overrides the doPost method to
 * handle the POST request. The method retrieves the data entered in the form
 * using the request object's getParameter method. validates the values of each
 * field using regular expressions. error messages are set in the session.
 */
public class CustomerRegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        //retrieves the data entered in the form using the request object's getParameter
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean nextPage = false;

        String error = "";
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
        String passRegEx = "[A-Z][A-Za-z1-9!@#$%^&*]{8,}";
        String emailRegEx = "[a-zA-Z0-9_%+-]+[.][a-zA-Z0-9_%+-]+@[a-zA-Z0-9-]+(.com)";
        //All below if are Checking regex with the entered data by user
        //error messages for invalid input data such as invalid name, password, phone number, date of birth, and gender.
        if (!name.matches(nameRegex)) {
            nameError = "*Error: The name must be alphabetical!";
            errorNum++;
        }

        if (!email.matches(emailRegEx)) {
            emailError = "*Error: Invalid Email Address.";
            errorNum++;
        }
        if (!password.matches(passRegEx)) {
            passError = "*Error: Invalid Password!,minimum length:8,First letter:Capital";
            errorNum++;
        }
        if (!phone.matches(phoneRegex)) {
            phoneError = "*Error: Enter a valid number";
            errorNum++;
        }
        if (!dob.matches(dobRgex)) {
            dobError = "*Error: You've to be 13 or older to register";//1900-2010
            errorNum++;
        }
        if (gender.isEmpty()) {
            genderError = "*Error: Select: (F)emale|(M)ale";
            errorNum++;
        }
        //If all fields are valid, the servlet checks if an Customer with the same email already exists in the database
        //If the Customer exists, an error message is set in the session then redirected back to the registration page
        //If the Customer does not exist, a new Customer object is created in the database using the create method of the CustomerSqlDAO. 
        if (errorNum == 0) {
            try {
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                Customer customerSql = customerSqlDAO.getCustomer(email);
                if (customerSql != null) {
                    error = "Customer already exists";
                    session.setAttribute("error", error);
                    request.getRequestDispatcher("register.jsp").include(request, response);
                } else {
                    nextPage = true;
                    customerSqlDAO.create(name, gender, dob, phone, email, password);
                    Customer customer = customerSqlDAO.getCustomer(email);

                    session.setAttribute("customer", customer);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CustomerRegisterServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            //error messages for each field if the validation fails and includes the "register.jsp" page again in the response.
        } else {

            session.setAttribute("nameerror2", nameError);
            session.setAttribute("emailerror2", emailError);
            session.setAttribute("passerror2", passError);
            session.setAttribute("phoneerror2", phoneError);
            session.setAttribute("doberror2", dobError);
            session.setAttribute("gendererror2", genderError);
            request.getRequestDispatcher("register.jsp").include(request, response);

        }
        if (nextPage) {
            request.getRequestDispatcher("main.jsp").include(request, response);
        }

    }
}
