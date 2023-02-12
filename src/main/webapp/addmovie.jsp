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
        <link href="css/formmovie.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        <title> Add Movie</title>
        <style>
            #cancellink {
                width: 100%;
                height: 36px;
                background-color: #009688;
                color: white;
                padding: 0.6rem;
                border: none;
                border-radius: 4px;
                text-decoration: none;
            }
        </style>
    </head>

    <body>

        <header>    
            <nav class="clear">
                <ul>
                    <li><a href="#">About The App</a></li>                        
                    <li><a href="main.jsp">Dashboard</a></li>
                    <li><a href="movies.jsp">MovieList</a></li>                    
                    <li><a href="/group2/LogoutServlet">Logout</a></li>
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>my movies.com</h1>
        </header>
        <%
            String yearerror = (String) session.getAttribute("yearError");
            session.removeAttribute("yearError");
            String runerror = (String) session.getAttribute("runError");
            session.removeAttribute("runError");
            String movieaddmsg = (String) session.getAttribute("movieAddMsg");
            session.removeAttribute("movieAddMsg");
            String fieldempty = (String) session.getAttribute("fieldEmpty");
            session.removeAttribute("fieldEmpty");
        %>
        <h1> Add Movie Details</h1>
        <form action="/group2/MovieAddServlet" method="POST" align="center">                  
            <span style="font-size: 12px; color: orange;"><%= (fieldempty != null) ? fieldempty : ""%></span>

            <div class="form" >
                <div class="field">

                    <label for="name">Movie Name</label><span class="user"> </span>
                    <input type="text" name="name" id="name" placeholder="Movie Name" class="main-input">
                    <span style="font-size: 12px; color: orange;"><%= (movieaddmsg != null) ? movieaddmsg : ""%></span>
                </div>
                <div class="field">
                    <label for="year">Year</label><span class="user"></span>
                    <input type="text" name="year" id="year" placeholder="Release year eg:(2015)" class="main-input">
                    <span style="font-size: 12px; color: orange;"><%= (yearerror != null) ? yearerror : ""%></span>
                </div>
                <div class="field">
                    <label for="runtime">Run Time</label><span class=""></span>
                    <input type="text" name="runtime" id="runtime" placeholder="Movie Runtime eg:2h 30m" class="main-input">
                    <span style="font-size: 12px; color: orange;"><%= (runerror != null) ? runerror : ""%></span>
                </div>
                <div class="field">
                    <label for="imgurl">Image link</label>
                    <input type="text" name="imgurl" id="imgurl" placeholder="Movie Image URL" class="main-input">
                </div>
                <div class="field">
                    <label for="imgurl">Trailer link</label>
                    <input type="text" name="vidurl" id="imgurl" placeholder="Movie Trialer URL" class="main-input">
                </div>

                <div class="field brief">

                    <label>Movie Genre</label>
                    <select name="genre" id="genre" class="genre-input">
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
                    <br> </br>
                    <label for="brief">Description</label>
                    <textarea name="description" id="description" placeholder="Movie Summary & Cast" class="main-input1"></textarea>
                    <div class="btn">

                        <div style="width: 100%; margin: 0 auto;">
                            <table width=100% ; border="1" cellpadding="20" cellspacing="70">
                                <tr>
                                    <td style="width: 200%; padding: 10px;" Row 1, Column 1> <input type="hidden" value="movie"> <button style="background-color:darkmagenta;" type ="submit">Add movie</button> </td> 
                                    <td style="width: 200%;  padding: 10px;" Row 1, Column 1> <a style="background-color:darkmagenta;" id="cancellink" href ="movies.jsp">Cancel</a></td>
                                </tr>

                            </table>
                        </div>
                    </div>  
                </div>
                <span style="font-size: 12px; align-content: center; color: orange;"><%= (fieldempty != null) ? fieldempty : ""%></span>
            </div>
        </div>
    </form>
</body>

</html>

