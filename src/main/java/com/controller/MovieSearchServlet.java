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
 * Servlet to Delete the movie by id in moviedb.movie_bookings & moviesdb.bookings through MovieSqlDAO.
 * @author 236365
 */
public class MovieSearchServlet extends HttpServlet {

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the session object to retrieve the "movieSqlDAO" and "movie" attributes
        HttpSession session = request.getSession();
        String movieSearchMsg="";       
        
        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
        Movie movie = new Movie();
        String name = request.getParameter("name");        

        if (name != null) {
            try {
                // get movie by name
                movie = movieSqlDAO.getMovie(name);
            } catch (SQLException ex) {
                Logger.getLogger(MovieSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        // Check  movie value and redirects accordingly
        if (movie != null) {
            session.setAttribute("movie", movie);            
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        } else {
            movieSearchMsg="Movie not found";
            session.setAttribute("movieSearchMsg",movieSearchMsg );
            request.getRequestDispatcher("movies.jsp").include(request, response);
        }

    }
}
