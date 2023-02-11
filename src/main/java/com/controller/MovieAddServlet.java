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

    //Servlet to add a movie into moviesdb.movies through MovieSqlDAO
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("com.controller.MovieAddServlet.doPost()");
        HttpSession session = request.getSession();

        String movieName = request.getParameter("name");
        String movieGenre = request.getParameter("genre");
        String movieDescription = request.getParameter("description");
        String movierunTime = request.getParameter("runtime");
        String movieImgurl = request.getParameter("imgurl");
        String movievidurl = request.getParameter("vidurl");
        String movieYr = request.getParameter("year");
        Movie movie = null;
        //Check if any string is null or empty
        if (movieName == null || movieGenre == null || movieDescription == null || movierunTime == null
                || movieImgurl == null || movievidurl == null || movieYr == null) {
            session.setAttribute("fieldEmpty", "Please enter value for all fields");
            request.getRequestDispatcher("moviedetails.jsp").include(request, response);
        }
        if (movieName.isEmpty() || movieGenre.isEmpty() || movieDescription.isEmpty() || movierunTime.isEmpty()
                || movieImgurl.isEmpty() || movievidurl.isEmpty() || movieYr.isEmpty()) {
            session.setAttribute("fieldEmpty", "Please enter value for all fields");
            request.getRequestDispatcher("addmovie.jsp").include(request, response);
        }

        String movieaddmsg = "";
        String yearerror = "";
        String runerror = "";
        int errorNum = 0;
        int movieYear=0;
        
        try {
            movieYear = Integer.parseInt(request.getParameter("year"));            
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
            runerror = "Format eg:2h 45m and runtime less than 5h";
            errorNum++;
        }
        //Check all conditon satisfy no e
        if (errorNum == 0) {
            try {
                MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
                movie = movieSqlDAO.getMovie(movieName);
                if (movie != null) {
                    movieaddmsg = "Movie already exists";
                    session.setAttribute("movieAddMsg", movieaddmsg);
                    request.getRequestDispatcher("addmovie.jsp").include(request, response);
                } else {
                    movieSqlDAO.create(movieName, movieGenre, movieYear, movieDescription, movierunTime, movieImgurl, movievidurl);
                    movie = movieSqlDAO.getMovie(movieName);
                    session.setAttribute("movie", movie);
                    request.getRequestDispatcher("moviedetails.jsp").include(request, response);
                }
            } catch (SQLException ex) {
                Logger.getLogger(MovieAddServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("runError", runerror);
            session.setAttribute("yearError", yearerror);
            request.getRequestDispatcher("addmovie.jsp").include(request, response);
        }

    }

}
