<%-- 
    Document   : movies
    Created on : 30-Jan-2023, 3:59:34 PM
    Author     : 236365
--%>

<%@page import="com.model.Admin"%>
<%@page import="com.model.Movie"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.MovieSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/styles.css" rel="stylesheet">
        <title>Movies</title>
    </head>
    <body>       
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="main.jsp">Home</a></li>                    
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li> 
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>my movies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>

        <%
            Admin admin = (Admin) session.getAttribute("admin");
            String movieDeleteMsg = (String) session.getAttribute("movieDeleteMsg");
            String movieAddMsg = (String) session.getAttribute("movieAddMsg");
       

            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            List<Movie> movies = movieSqlDAO.getMovies();

        %>        
        <div>
            <form action="/group2/MovieSearchServlet" method="POST">
                <input name="name" type="text" placeholder="Movie Name/Title">
                <input type="submit" value="search">                                
            </form>
        </div>

        <%  
            String moviesearchmsg = (String) session.getAttribute("movieSearchMsg");
            if (moviesearchmsg != null) {
                session.removeAttribute("movieSearchMsg");
        %>
        <div id="notification" style="display: none;">
            <p><%= moviesearchmsg%></p>
        </div>

        <script>
            document.getElementById("notification").style.display = "block";
            setTimeout(function () {
                document.getElementById("notification").style.display = "none";
            }, 3000); // 3000 milliseconds = 3 seconds
        </script>
        <%
            }
        %>    
        <br>
        <br> 

        <% if (admin != null) {%>
        <form action="addmovie.jsp" method="POST">
            <input name="addmovie" type="submit" value="AddMovie">
        </form>
        <% } %>

        <%
            if (movieDeleteMsg != null) {%>
        <h3><%=movieDeleteMsg%></h3>
        <% } %>

        <table>
            <% for (Movie movie : movies) {%>
            <tr>
                <td>
                    <a href="http://localhost:8080/group2/MovieViewServlet?Id=<%=movie.getid()%>">
                        <img src="<%= movie.getImgUrl()%>" width="100px" height="100px">
                    </a>                        
                </td>
                <td><%= movie.getName()%></td>
                <td><%= movie.getGenre()%></td>
                <td><%= movie.getYear()%></td>
                <td><%= movie.getRuntime()%></td>
            </tr> 
            <%}%>

        </table>           
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>             

    </body>
</html>
