<%-- 
    Document   : moviedelete
    Created on : 04-Feb-2023, 5:11:55 PM
    Author     : 236365
--%>

<%@page import="com.model.Movie"%>
<%@page import="com.model.dao.MovieSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Delete Page</title>
    </head>
    <body>
        <%
            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            Movie movie = (Movie) session.getAttribute("movie");
        %>
        <form action="/MovieDeleteServlet">
            <legend> Are you sure you want to delete <%=movie.getName()%></legend>
            <input type="hidden" value="<%=movie.getid()%>" name="mid">
            <input type="submit" value="delete">                     
        </form>
    </body>
</html>



