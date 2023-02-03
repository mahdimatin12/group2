<%-- 
    Document   : movieview
    Created on : 31-Jan-2023, 7:09:06 PM
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
        <title>Movie View</title>
    </head>
    <body>
        
        <form class="searchForm" action="/group2/MovieSearchServlet" method="POST">
            <input name="id" type="text" id="id" placeholder="search by Id">
            <input id="searchbtn" type="submit" value="search">
        </form> 
        
        <%
            MovieSqlDAO movieSqlDAO = (MovieSqlDAO)session.getAttribute("movieSqlDAO");
            //List<Movie> movies= movieSqlDAO.getMovies();             
        %>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="register.jsp">SIGN UP</a></li>                    
                    <li><a href="login.jsp">LOGIN</a></li>
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
        
        <article class="main">
            
              <form action="/group2/MovieSearchServlet" method="POST">
                        <input name="id" type="text" placeholder="search by id" id="id">
                        <input type="submit" value="search">

              </form> 

            <table>
                <% //for(Movie movie:movies){%>
                <tr>
                    <td>
                        <a href="moviedetails.jsp"><img src="<%= movie.getImgUrl()%>" width="150px" height="180px"></a>                        

                    </td>
                    <td><%= //movie.getName()%></td>
                    <td><%=// movie.getGenre()%></td>
                </tr> 
                <%}%>
               
            </table>
            
        </article>
        
         <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>         
    </body>
</html>
