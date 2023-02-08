/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.Movie;
import com.model.dao.MovieSqlDAO;
import static com.oracle.webservices.api.databinding.DatabindingModeFeature.ID;
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
        Movie movie=(Movie)session.getAttribute("movie");
        String yearRegex = "(20[0-1][0-9])";
        String runRegex = "([0-4]|4[0-9])\\s*h\\s*([0-5][0-9]|[0-5]*)\\s*m";
        
        int Id=movie.getid();
        String movieName = request.getParameter("uname");
        String movieGenre = request.getParameter("ugenre");
        int movieYear = Integer.parseInt(request.getParameter("uyear"));
        String movieDescription = request.getParameter("udescription");
        String movierunTime = request.getParameter("uruntime");
        String movieImgurl = request.getParameter("uimgurl");
        String movievidurl = request.getParameter("uvidurl");

        String movieUpdateMsg = "Update Successful";
        String yearError = "";
        String runError = "";
        int errorNum = 0;
      String movieYr = request.getParameter("uyear");
        if (!movieYr.matches(yearRegex)) {
            yearError = "Year between 2000-2023 only";
            session.setAttribute("yearerror", yearError);
            errorNum++;            
        }
        if (!movierunTime.matches(runRegex)) {
            runError = "Incorrect format eg:2h 45m and runtime less than 5hrs";
            session.setAttribute("runerror", runError);
            errorNum++;            
        }       
        
        if (errorNum == 0) {
            try {

                movieSqlDAO.update(movieName, movieGenre, movieYear, movieDescription, movierunTime, movieImgurl, movievidurl, Id);
                movie=movieSqlDAO.getMovie(Id);
                session.setAttribute("movieUpdateMsg", movieUpdateMsg);
                session.removeAttribute("yearerror");              
                session.removeAttribute("runerror");
                session.setAttribute("movie", movie);
                request.getRequestDispatcher("moviedetails.jsp").include(request, response);

            } catch (SQLException ex) {
                Logger.getLogger(MovieUpdateServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }else{            
             
              request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        }
       

    }

}
