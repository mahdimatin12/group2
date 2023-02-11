<%-- 
    Document   : addmovie
    Created on : 31-Jan-2023, 11:49:15 PM
    Author     : 236365
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Movie</title>
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
        <div>
        <%
            String yearerror = (String) session.getAttribute("yearError");
            session.removeAttribute("yearerror");
            String runerror = (String) session.getAttribute("runError");
            session.removeAttribute("runerror");
            String movieaddmsg = (String) session.getAttribute("movieAddMsg");
            session.removeAttribute("movieAddMsg");
            String fieldempty = (String) session.getAttribute("fieldEmpty");
            session.removeAttribute("fieldEmpty");
        %>
        <h1> Movie Details</h1>
        <br>
        
        
        <!--Form for Adding a movie -->
        <form action="/group2/MovieAddServlet" method="POST" align="center">
            <span style="font-size: 10px; color: orange;"><%= (movieaddmsg != null) ? movieaddmsg : ""%></span>
            <span style="font-size: 10px; color: orange;"><%= (fieldempty != null) ? fieldempty : ""%></span>
            <div>
                <label>Movie Title/Name</label>
                <input name="name"  id="name" type="text" placeholder="Movie Title/Name"%>
            </div>
            <div>
                <label>Movie Genre</label>
            <select name="genre" id="genre">
                <option value="">Choose genre</option>
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
                <input name="year"  id="year" type="text" placeholder="Release year eg:(2015)">
                <span style="font-size: 10px; color: orange;"><%= (yearerror != null) ? yearerror : ""%></span>
            <div>
                <label>Description </label>
                <input name="description" id="description" type="text" placeholder="Summary,Cast...">
            </div>            
            <div>
                 
                <label>Run time </label>                
                <input name="runtime" id="runtime" type="text" placeholder="movie length">
                <span style="font-size: 10px; color: orange;"><%= (runerror != null) ? runerror : ""%></span>
            </div>
             <div>
                <label>Image URL </label>
                <input name="imgurl" id="imgurl"   type="text" placeholder="Add image URL Link">
            </div>      
            <div>
                <label>Video URL </label>
                <input name="vidurl" id="vidurl" type="text" placeholder="Add video URL Link">
            </div>          
            
            <div><input type="hidden" value="movie">
            <input type="submit" value="Add Movie" style="cursor: pointer">
            </div>
        </form>
        </div>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>

    </body>
</html>

