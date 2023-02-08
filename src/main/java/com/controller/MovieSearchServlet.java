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
public class MovieSearchServlet extends HttpServlet {

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String movieSearchMsg="";
        String IdRegex = "^[0-9]{6}$";
        String Iderror = "Incorrect ID format";
        
        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
        Movie movie = null;
        String name = request.getParameter("name");
        

        if (name != null) {
            try {
                movie = movieSqlDAO.getMovie(name);
            } catch (SQLException ex) {
                Logger.getLogger(MovieSearchServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
        if (movie != null) {
            session.setAttribute("movie", movie);
            
            //session.removeAttribute("movieSearchMsg");

            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        } else {
            movieSearchMsg="Movie does not exist";
            session.setAttribute("movieSearchMsg",movieSearchMsg );
            session.removeAttribute("movie");
            request.getRequestDispatcher("movies.jsp").include(request, response);
        }

    }
}
