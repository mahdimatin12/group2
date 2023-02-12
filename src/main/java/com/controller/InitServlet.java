package com.controller;

import com.model.dao.AdminSqlDAO;
import com.model.dao.BookingSqlDAO;
import com.model.dao.CustomerSqlDAO;
import com.model.dao.MovieSqlDAO;
import com.model.dao.SqlDBConnector;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
*InitServlet" which is used to initialize some resources and store them as attributes in an HTTP session.
* servlet is written using the Java Servlet API and is used to handle HTTP requests and responses.
* InitServlet serves as a centralized place for initializing resources
*/

public class InitServlet extends HttpServlet {

    private CustomerSqlDAO customerSqlDAO;
    private AdminSqlDAO adminSqlDAO;
    private BookingSqlDAO bookingSqlDAO;
    private SqlDBConnector dBConnector;
    private Connection connection;
    private MovieSqlDAO movieSqlDAO;
    
    //servlet establishes a connection to a database using the SqlDBConnector class and then creates instances of the DAO classes
    // stored classes as attributes in the HTTP session
    @Override
    public void init() {
        try {
            dBConnector = new SqlDBConnector();
            connection = dBConnector.connection(); //opening connection with the db
            customerSqlDAO = new CustomerSqlDAO(connection);
            adminSqlDAO = new AdminSqlDAO(connection);
            bookingSqlDAO = new BookingSqlDAO(connection);
            movieSqlDAO = new MovieSqlDAO(connection);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   // doGet is called when an HTTP GET request is made to the servlet. 
    //this method  creates a new HTTP session and stores the instances of the DAO classes as attributes in the session
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("adminSqlDAO", adminSqlDAO);
        session.setAttribute("customerSqlDAO", customerSqlDAO);
        session.setAttribute("bookingSqlDAO", bookingSqlDAO);
        session.setAttribute("movieSqlDAO", movieSqlDAO);
    }

    // destroy method of the servlet is called when the servlet is being destroyed
    // It closes the connection to the database 
    @Override
    public void destroy() {
        try {
            dBConnector.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
