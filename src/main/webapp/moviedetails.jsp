<%-- 
    Document   : moviedetails
    Created on : 30-Jan-2023, 10:59:32 PM
    Author     : 236365
--%>

<%@page import="com.model.Movie"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.MovieSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet">
        <title>Movie</title>
    </head>
    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>                                     
                    <li><a href="main.jsp">HOME</a></li>
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
        <%
            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            Movie movie = (Movie) session.getAttribute("movie");
        %>

        <div style="margin: auto;">
            <form>
                <table align ="center">
                    <caption>Movie</caption>
                    <tr><td>ID: </td><td><input type="text" name="ID" value="<%=movie.getid()%>" readonly="true" /></td></tr>
                    <tr><td>Name: </td><td><input type="text" name="name" value="<%=movie.getName()%>" /></td></tr>
                    <tr><td>Genre/Category: </td><td><input type="text" name="genre" value="<%=movie.getGenre()%>" /></td></tr>
                    <tr><td>Release Year: </td><td><input type="text" name="year" value="<%=movie.getYear()%>" /></td></tr>
                    <tr><td>Summary: </td><td><input type="text" name="runtime" value="<%= movie.getDescription()%>"/></td></tr>
                    <tr><td>Run time: </td><td><input type="text" name="name" value="<%=movie.getRuntime()%>" /></td></tr>
                    <tr><td>image URL: </td><td><img src="<%= movie.getImgUrl()%>" width="100px" height="100px"></td></tr>
                    <tr><td>Trailer URL: </td><td><iframe src="<%= movie.getVidUrl()%>"></iframe></td></tr>
                </table>
            </form>
        </div>
 
    
    
         <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    
    </body>
    </html>

    

