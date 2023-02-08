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
public class MovieAddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String movieName = request.getParameter("name");
        String movieGenre = request.getParameter("genre");
        int movieYear = Integer.parseInt(request.getParameter("year"));
        String movieDescription = request.getParameter("description");
        String movierunTime = request.getParameter("runtime");
        String movieImgurl = request.getParameter("imgurl");
        String movievidurl = request.getParameter("vidurl");

        String yearRegex = "(20[0-1][0-9])";
        String runRegex = "([0-4]|4[0-9])\\s*h\\s*([0-5][0-9]|[0-5]*)\\s*m";//^[+0]\\d{1,2}\\d{6,11}$";

        String movieAddMsg = "";
        String yearError = "";
        String runError = "";
        int errorNum = 0;

        MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
        Movie movie = null;
       String movieYr = request.getParameter("year");

        if (!movieYr.matches(yearRegex)) {
            yearError = "Year between 2000-2023 only";
            errorNum++;
            session.setAttribute("yearerror", yearError);
            
        }
        if (!movierunTime.matches(runRegex)) {
            runError = "Incorrect format eg:2h 45m and runtime less than 5hrs";
            errorNum++;
            session.setAttribute("runerror", runError);
        }

        if (errorNum == 0) {
            try {
                movie = movieSqlDAO.getMovie(movieName);
                 if (movie != null) {
                movieAddMsg = "Movie already exists";
                session.setAttribute("movieAddMsg", movieAddMsg);
                request.getRequestDispatcher("addmovie.jsp").include(request, response);
            } else {
                movieSqlDAO.create(movieName, movieGenre, movieYear, movieDescription, movierunTime, movieImgurl, movievidurl);
                movieAddMsg = "Movie added successfully";
                session.setAttribute("movieAddMsg", movieAddMsg);
                request.getRequestDispatcher("addmovie.jsp").include(request, response);
                errorNum=0;yearError="";runError="";
                
            }
            } catch (SQLException ex) {
                Logger.getLogger(MovieAddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }     
        }else{
           request.getRequestDispatcher("addmovie.jsp").include(request, response);
        }
        
    }
}
