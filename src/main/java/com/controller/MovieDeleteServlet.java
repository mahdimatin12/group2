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
public class MovieDeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Servlet to Delete the  movie by id in moviedb.movie_bookings&moviesdb.bookings through MovieSqlDAO
  
        HttpSession session = request.getSession();
        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
         // Get the movie object from the session
        Movie movie = (Movie)session.getAttribute("movie");
        int id = movie.getid();
        String movieDeleteMsg = "";
        if (id != 0) {
            try {
                // Call the "delete" method of MovieSqlDAO to delete the movie
                movieSqlDAO.deletebooking(id);
                movieSqlDAO.delete(id);
                // Set the success message in the session
                movieDeleteMsg = "Movie deleted successfully";
                session.setAttribute("movieDeleteMsg", movieDeleteMsg);
                  // Remove the movie object from the session
                session.removeAttribute("movie");
                // Redirect to the movies.jsp page
                request.getRequestDispatcher("movies.jsp").include(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(MovieDeleteServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
