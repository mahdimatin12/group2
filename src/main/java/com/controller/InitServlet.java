/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class InitServlet extends HttpServlet {

    private CustomerSqlDAO customerSqlDAO;
    private AdminSqlDAO adminSqlDAO;
    private BookingSqlDAO bookingSqlDAO;
    private SqlDBConnector dBConnector;
    private Connection connection;
    private MovieSqlDAO movieSqlDAO;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("adminSqlDAO", adminSqlDAO);
        session.setAttribute("customerSqlDAO", customerSqlDAO);
        session.setAttribute("bookingSqlDAO", bookingSqlDAO);
        session.setAttribute("movieSqlDAO", movieSqlDAO);
    }

    @Override
    public void destroy() {
        try {
            dBConnector.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(InitServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
