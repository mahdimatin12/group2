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
                    
                    String yearerror = (String) session.getAttribute("upyearError");
                    session.removeAttribute("upyearError");
                    String runerror = (String) session.getAttribute("uprunError");
                    session.removeAttribute("uprunError");
                    String fieldempty = (String) session.getAttribute("fieldEmpty");
                    session.removeAttribute("fieldEmpty");
                    Movie movie = (Movie) session.getAttribute("movie");
                    
                %>
                <h1> Movie Details</h1>
                <br>
                <form  action="/group2/MovieUpdateServlet" method="POST" align="center">
                    <span style="font-size: 10px; color: orange;"><%= (fieldempty != null) ? fieldempty :""%></span>
                    <div>                        
                        <label>Movie ID</label>                        
                        <input name="uId" type="text" value="<%=movie.getid()%>" readonly="true">
                    </div>
                    <div>
                        <label>Movie Title/Name</label>
                        <input name="uname" type="text" value="<%=movie.getName()%>" readonly="true">
                    </div>
                    <div>
                        <label>Movie Genre</label>
                        <select name="ugenre" id="genre" value="" >
                            <option value="<%=movie.getGenre()%>"><%=movie.getGenre()%></option>
                            <option value="Action">Action</option>
                            <option value="Adventure">Adventure</option>
                            <option value="Animation">Animation</option>
                            <option value="Comedy">Comedy</option>
                            <option value="Drama">Drama</option>
                            <option value="Fantasy">Fantasy</option>
                            <option value="Horror">Horror</option>
                            <option value="Romance">Romance</option>                        
                            <option value="Sci-fi">Sci-Fi</option>
                            <option value="Thriller">Thriller</option>
                            <option value="war">War</option>
                        </select>
                    </div>
                    <div>
                        <label> Release Year </label>                        
                        <input name="uyear" type="text" value="<%= movie.getYear()%>" %>
                        <span style="font-size: 10px; color: orange;"><%= (yearerror != null) ? yearerror : ""%></span>
                    </div>
                    <div>
                        <label>Description </label>
                        <input name="udescription" id="description" type="text" value="<%= movie.getDescription()%>">
                    </div>
                    <div>
                        <label>Run time               
                        </label>
                        <input name="uruntime" id="runtime" type="text" value="<%= movie.getRuntime()%>"%>
                        <span style="font-size: 10px; color: orange;"><%= (runerror != null) ? runerror : ""%></span>
                    </div>
                    <div>
                        <label>Image URL </label>
                        <input name="uimgurl" id="imgurl" type="text" value="<%= movie.getImgUrl()%>">
                    </div>      
                    <div>
                        <label>Video URL </label>
                        <input name="uvidurl" id="vidurl" type="text" value="<%= movie.getVidUrl()%>">
                    </div>          
                    <div><input name="submitted" type="hidden" value="movie">
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


