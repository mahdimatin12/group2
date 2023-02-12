/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Movie;
import com.model.dao.MovieSqlDAO;
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
 *
 * @author 236365
 */
public class MovieViewServlet extends HttpServlet {
// Servlet get movie by name and shows movies details in moviedetails.jsp

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String movieSearchMsg = "";

        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
        Movie movie = null;
        String moviename = (request.getParameter("name"));
        try {
            movie = movieSqlDAO.getMovie(moviename);
        } catch (SQLException ex) {
            Logger.getLogger(MovieSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        // checking if movie not null
        if (movie != null) {
            session.setAttribute("movie", movie);
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        } else {
            movieSearchMsg = "Movie does not exist";
            session.setAttribute("movieSearchMsg", movieSearchMsg);
            session.removeAttribute("movie");
            request.getRequestDispatcher("movies.jsp").include(request, response);
        }

    }

}
