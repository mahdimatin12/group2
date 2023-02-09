<%-- 
    Document   : addmovie
    Created on : 31-Jan-2023, 11:49:15 PM
    Author     : 236365
--%>

<%@page import="com.model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Movie</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <header>    
            <nav class="clear">
                <ul>
                    <li><a href="#">About The App</a></li>                        
                    <li><a href="index.jsp">Home</a></li>
                    <li><a href="movies.jsp">Movies</a></li>
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>my movies.com</h1>
        </header>
        <article >
            <div >
                <%
                    String yearerror = (String) session.getAttribute("yearError");
                    String runerror = (String) session.getAttribute("runError");
                    String movieupdatemsg = (String) session.getAttribute("movieAddMsg");
                    Movie movie = (Movie) session.getAttribute("movie");
                %>
                
                <h1> Movie Details</h1>
                <br>
                <h3><span style="font-size: 10px; color: orange;"><%= (movieupdatemsg != null) ? movieupdatemsg : ""%></span></h3>
                <h3><span style="font-size: 10px; color: orange;"><%= (yearerror != null) ? yearerror : ""%></span></h3>
                <h3><span style="font-size: 10px; color: orange;"><%= (runerror != null) ? runerror : ""%></span></h3>
                <form id="form1" action="/group2/MovieUpdateServlet" method="POST" align="center">
                    <div>
                        <label>Movie ID</label>
                        <input name="uId" type="text" value="<%=movie.getid()%>" readonly="true">
                    </div>
                    <div>
                        <label>Movie Title/Name</label>
                        <input name="uname" type="text" value="<%=movie.getName()%>">
                    </div>
                    <div>
                        <label>Movie Genre</label>
                        <select name="ugenre" id="genre">
                            <option value="">--Please choose genre--</option>
                            <option value="action">Action</option>
                            <option value="adventure">Adventure</option>
                            <option value="animation">Animation</option>
                            <option value="comedy">Comedy</option>
                            <option value="drama">Drama</option>
                            <option value="fantasy">Fantasy</option>
                            <option value="horror">Horror</option>
                            <option value="romance">Romance</option>                        
                            <option value="sci-fi">Sci-Fi</option>
                            <option value="thriller">Thriller</option>
                            <option value="war">War</option>
                        </select>
                    </div>
                    <div>
                        <label>Release Year </label>
                        <input name="uyear" type="text" value="<%= movie.getYear()%>" %>">

                    </div>
                    <div>
                        <label>Description </label>
                        <input name="udescription" id="description" type="text" value="<%= movie.getDescription()%>">
                    </div>

                    <div>
                        <label>Run time </label>
                        <input name="uruntime" id="runtime" type="text" value="<%= movie.getRuntime()%>"%>">
                    </div>
                    <div>
                        <label>Image URL </label>
                        <input name="uimgurl" id="imgurl" type="text" value="<%= movie.getImgUrl()%>">
                    </div>      
                    <div>
                        <label>Video URL </label>
                        <input name="uvidurl" id="vidurl" type="text" value="<%= movie.getImgUrl()%>">
                    </div>            

                    <div><input type="hidden" value="movie">
                        <input type="submit" value="Update" style="cursor: pointer">
                    </div>
                </form>
            </div>
        </article>

        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>

    </body>
</html>


