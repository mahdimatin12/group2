<%-- 
    Document   : movies
    Created on : 30-Jan-2023, 3:59:34 PM
    Author     : 236365
--%>

<%@page import="com.model.Customer"%>
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
                    <li><a href="main.jsp">Dashboard</a></li>                    
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li> 
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>my movies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
            
        </header>
        <%
            Admin admin = (Admin) session.getAttribute("admin");
            Customer customer = (Customer) session.getAttribute("customer");

            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            List<Movie> movies = movieSqlDAO.getMovies();

            String moviedeletemsg = (String) session.getAttribute("movieDeleteMsg");
            String movieAddMsg = (String) session.getAttribute("movieAddMsg");

        %> 
        <span style="font-size: 10px; color: orange;"><%= (admin!= null) ? admin.getName():customer.getName()  %>
        <div>
            <form action="/group2/MovieSearchServlet" method="POST">
                <input name="name" type="text" placeholder="Movie Name/Title">
                <input type="submit" value="search">                                
            </form>
        </div>
        <%  String moviesearchmsg = (String) session.getAttribute("movieSearchMsg");
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
        <%}%>    
        <br>
        <% 
            if (moviedeletemsg != null) {
                session.removeAttribute("moviedeleteMsg");
        %>
        <div id="notification1" style="display: none;">
            <p><%= moviedeletemsg%></p>
        </div>
        <script>
            document.getElementById("notification1").style.display = "block";
            setTimeout(function () {
                document.getElementById("notification1").style.display = "none";
            }, 3000); // 3000 milliseconds = 3 seconds
        </script>
        <%}%>

        <br>

        <% if (admin!= null) {%>
        <form action="addmovie.jsp" method="POST">
            <input name="addmovie" type="submit" value="AddMovie">
        </form>
        <% } %>

        <% if (customer!= null) {%>
        <form action="moviebookingpage.jsp">
            <input type="submit" value="Book">
        </form>
        <% }%>
        
        <br>
        <br>       

        <table>
            <% for (Movie movie : movies) {%>
            <tr>
                <td>
                    <a href="http://localhost:8080/group2/MovieViewServlet?name=<%=movie.getName()%>">
                        <img src="<%= movie.getImgUrl()%>" width="100px" height="100px">
                    </a>                        
                </td>
                <td><%= movie.getName()%></td>
                <td><%= movie.getGenre()%></td>
                <td><%= movie.getYear()%></td>               
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
