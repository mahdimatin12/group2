/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Movie;
import com.model.dao.MovieSqlDAO;
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
        int id = Integer.parseInt(request.getParameter("" + "id"));

        MovieSqlDAO movieSqlDAO = (MovieSqlDAO)session.getAttribute("movieSqlDAO");

        Movie movie = null;
        try {
            movie = movieSqlDAO.getMovie(id);
        } catch (SQLException ex) {
        }

        if (movie != null) {
            session.setAttribute("movie", movie);
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
            
        } else {
            session.setAttribute("Movieerror", "Movie id does not exist");
            request.getRequestDispatcher("movies.jsp").include(request, response);
        }
    }

}
