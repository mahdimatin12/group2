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
public class MovieUpdateServlet extends HttpServlet {
    //Servlet to update a movie by id in moviedb.movies through MovieSqlDAO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");        

        int Id = Integer.parseInt(request.getParameter("uId"));
        String movieName = request.getParameter("uname");

        String movieGenre = request.getParameter("ugenre");
        String movieDescription = request.getParameter("udescription");
        String movierunTime = request.getParameter("uruntime");
        String movieImgurl = request.getParameter("uimgurl");
        String movievidurl = request.getParameter("uvidurl");
        String movieYr = request.getParameter("uyear");
        //Check if any field is empty
        if (movieGenre == null || movieDescription == null || movierunTime == null
                || movieImgurl == null || movievidurl == null || movieYr == null) {
            session.setAttribute("fieldEmpty", "Please enter value for fields");
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        }
        
        if (movieName.isEmpty()||movieGenre.isEmpty() || movieDescription.isEmpty() || movierunTime.isEmpty()
           || movieImgurl.isEmpty() || movievidurl.isEmpty() || movieYr.isEmpty()) {
            session.setAttribute("fieldEmpty", "Please enter value for all fields");
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        }              

        String movieupdatemsg = "";
        String yearerror = "";
        String runerror = "";
        int errorNum = 0;
        int movieYear=0;
         
         
        try {
            movieYear = Integer.parseInt(request.getParameter("uyear"));            
        } catch (NumberFormatException e) {
           yearerror = "Year format(YYYY,and between 2000-2023) ";
           session.setAttribute("yearError", yearerror);
           request.getRequestDispatcher("addmovie.jsp").include(request, response);
        }
         
         
        String yearRegex = "(20[0-1][0-9]|20[2][0-3])";
        String runRegex = "^([0-3]h\\s*[0-5][0-9]m\\s*)$";

        if (!movieYr.matches(yearRegex)) {
            yearerror = "Year format(YYYY,and between 2000-2023) ";
            errorNum++;
        }

        if (!movierunTime.matches(runRegex)) {
            runerror = "Runtime Format eg:2h 45m and less than 4h";
            errorNum++;
        }
        //Check all conditon satisfy
        if (errorNum == 0) {
            try {
                movieSqlDAO.update(movieName, movieGenre, movieYear, movieDescription, movierunTime, movieImgurl, movievidurl, Id);
                session.removeAttribute("movie");
                Movie movie = movieSqlDAO.getMovie(Id);
                session.setAttribute("movie", movie);
                request.getRequestDispatcher("moviedetails.jsp").include(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MovieAddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } //Redirect to movieupdate.jsp and set errors in session
        else {
            session.setAttribute("uprunError", runerror);
            session.setAttribute("upyearError", yearerror);
            request.getRequestDispatcher("movieupdate.jsp").include(request, response);
        }

    }
}
