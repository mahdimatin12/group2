/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;
import com.model.Movie;
import com.model.dao.MovieSqlDAO;
import java.io.IOException;
//import java.io.PrintWriter;
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
public class MovieAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        MovieSqlDAO movieSqlDAO = (MovieSqlDAO)session.getAttribute("movieSqlDAO");
        Movie movie = null;  
        
        String name = request.getParameter("name");
        String genre = request.getParameter("genre");
        String year = request.getParameter("year");
        String description= request.getParameter("description");
        String runtime = request.getParameter("runtime");
        String imgurl = request.getParameter("imgurl");
        String vidurl = request.getParameter("vidurl");
        String  msg="Movie added successfully";
                
     
        try {
            movieSqlDAO.create(name, genre, year, description, runtime, imgurl, vidurl);
            session.setAttribute("moviemsg",msg);
            request.getRequestDispatcher("movies.jsp").include(request, response);
            
        } catch (SQLException ex) {
            
        }
        
    }

}
