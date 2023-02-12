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
        <link href="css/movie.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <link href="css/sorttable.css" rel="stylesheet">        

        <title>Movies</title>
        <style>
            #keywords th {
                color: darkmagenta;
            }
            #addbtn {
                float: right;
            width: 10%;
            height: 36px;
            background-color:darkmagenta;
            color: white;
            padding: 0.6rem;
            border: none;
            border-radius: 4px;
             
            }
            #srchbtn{              
            width: 10%;
            height: 36px;
            background-color:darkmagenta;
            color: white;
            padding: 0.6rem;
            border: none;
            border-radius: 4px;
             
            }
        </style>
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
        <span style="display:none"><%= (admin != null) ? admin.getName() : customer.getName()%></span>
            
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
            <br>
            <br> 

            <!-- display data from movies table-->

            <br><br>
            <div id="wrapper">
                <div>
                <form action="/group2/MovieSearchServlet" method="POST">
                    <input style="padding:9px;" name="name" type="text" placeholder="Movie Name/Title">
                    <input id ="srchbtn" type="submit" value="search">                                
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
            <% }%>    
            <h1  style="color:darkmagenta">Movie List</h2>
                <% if (admin != null) {%>
            <form action="addmovie.jsp" method="POST">
                <input id="addbtn" name="addmovie" type="submit" value="AddMovie">
            </form>
            <% } %>
                <% if (customer != null) {%>
            <form action="step1.jsp">
                <input type="submit" value="Book">
            </form>
            <% }%>
                    <table id="keywords" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th><span>Movie ID</span></th>
                                <th><span>Movie Name</span></th>
                                <th><span>Movie genre</span></th>
                                <th><span>Release year</span></th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (Movie movie1 : movies) {%>
                            <tr style="color:black;vertical-align: middle">
                                <td>
                                    <a href="http://localhost:8080/group2/MovieViewServlet?name=<%=movie1.getName()%>">
                                        <img src="<%= movie1.getImgUrl()%>" width="100px" height="100px">
                                    </a>                        
                                </td>

                                <td style="color:black;vertical-align: middle"><%= movie1.getName()%></td>
                                <td style="color:black;vertical-align: middle"><%= movie1.getGenre()%></td>
                                <td style="color:black;vertical-align: middle"><%= movie1.getYear()%></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
            </div>     
            <footer>
                <p>SIUA 2023, UST, Sydney.
                <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
                <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
            </footer>             
    </body>
</html>
