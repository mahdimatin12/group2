<%@page import="java.util.List"%>
<%@page import="com.model.Movie"%>
<%@page import="com.model.dao.MovieSqlDAO"%>
<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Movies: Assessment 3</title>
        <link href="css/styles.css" rel="stylesheet">
        <link href="css/home.css" rel="stylesheet">
        <style>

            .loginContent {
                margin-left: 10%;
                width: 80%;
                height: 100%;
                box-shadow: 0.2px;
            }
        </style>
    </head>

    <body>
        <jsp:include page="/InitServlet" flush="true"/>
        <%
            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            List<Movie> movies = movieSqlDAO.getHomeMovies();
            
        String figID = "figure"; 
        %>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="aboutus.jsp">ABOUT THE APP</a></li>
                    <li><a href="register.jsp">SIGN UP</a></li>                    
                    <li><a href="login.jsp">LOGIN</a></li>


                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>


        <!-- main content goes here -->
        <article class="main">

            <h1 style="text-align:center"><----- All Movies ----></h1>
            <div class="loginContent">
                <div class="gallery">
                    <% for (Movie movie : movies) {%>
                    <figure id="<%=figID%>">
                        <img src="<%=movie.getImgUrl()%>" width="200" height="200" alt="<%=movie.getName()%>">
                        <figcaption><strong><%=movie.getYear()%></strong>
                            <%=movie.getGenre()%></figcaption>
                    </figure>
                    <%}%>
                </div>
            </div>
        </article>

        <script>
            document.getElementById('figure').onclick = function () {
                alert("You need to login in order to access movies!");
            };
        </script>
    </article>

    <footer>
        <p>SIUA 2023, UST, Sydney.
        <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
        <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
    </footer>


</body>
</html>
