package com.controller;

import com.model.Customer;
import com.model.dao.CustomerSqlDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ShowCustomerInfoServlet")
public class CustomerEmailViewServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        String emailView = request.getParameter("emailView");

        Customer customer = null;
        if (emailView != null) {
            try {
                customer = customerSqlDAO.getCustomer(emailView);
                session.setAttribute("emailView", emailView);
            } catch (SQLException ex) {
                Logger.getLogger(CustomerEmailViewServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            customer = (Customer) session.getAttribute("customer");
        }
        session.setAttribute("customer", customer);
        request.getRequestDispatcher("customerEmailView.jsp").include(request, response);
    }
}
