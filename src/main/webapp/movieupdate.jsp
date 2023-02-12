<%-- 
    Document   : updatemovie
    Created on : 31-Jan-2023, 11:49:15 PM
    Author     : 236365
--%>

<%@page import="com.model.Movie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/formmovie.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">

        <title>Update Movie</title>
    </head>

    <body>
        <header>    
            <nav class="clear">
                <ul>
                    <li><a href="#">About The App</a></li>                        
                    <li><a href="main.jsp">Dashboard</a></li>
                    <li><a href="movies.jsp">MovieList</a></li>
                    <li><a href="moviedetails.jsp">Back</a></li>
                    <li><a href="/group2/LogoutServlet">Logout</a></li>
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>my movies.com</h1>
        </header>

        <%
            String yearerror = (String) session.getAttribute("upyearError");
            session.removeAttribute("upyearError");
            String runerror = (String) session.getAttribute("uprunError");
            session.removeAttribute("uprunError");
            String fieldempty = (String) session.getAttribute("fieldEmpty");
            session.removeAttribute("fieldEmpty");
            Movie movie = (Movie) session.getAttribute("movie");

        %>

        <h1> Update Movie Details</h1>
        <br>
        <form  action="/group2/MovieUpdateServlet" method="POST" align="center">
            <span style="font-size: 10px; color: orange;"><%= (fieldempty != null) ? fieldempty : ""%></span>

            <div class="form" >
                <div class="field"> 

                    <label for="ID">Movie ID</label><span class="user"> </span>
                    <input type="text" name="uId" id="ID" value="<%=movie.getid()%>"  readonly="true" disabled class="main-input">

                </div>

                <div class="field"> 

                    <label for="name">Movie Name</label><span class="user"> </span>
                    <input type="text" name="uname" id="name" value="<%=movie.getName()%>" readonly="true" disabled  class="main-input">
                    
                </div>

                <div class="field">
                    <label for="year">Year</label><span class="user"></span>
                    <input type="text" name="uyear" id="year" value="<%= movie.getYear()%>" class="main-input">
                    <span style="font-size: 10px; color: orange;"><%= (yearerror != null) ? yearerror : ""%></span>
                </div>
                <div class="field">
                    <label for="runtime">Run Time</label><span class=""></span>
                    <input type="text" name="uruntime" id="runtime" value="<%= movie.getRuntime()%>" class="main-input">
                    <span style="font-size: 10px; color: orange;"><%= (runerror != null) ? runerror : ""%></span>
                </div>
                <div class="field">
                    <label for="imgurl">Image link</label>
                    <input type="text" name="uimgurl" id="imgurl" value="<%= movie.getImgUrl()%>" class="main-input">
                </div>
                <div class="field">
                    <label for="imgurl">Trailer link</label>
                    <input type="text" name="uvidurl" id="imgurl" value="<%= movie.getVidUrl()%>" class="main-input">
                </div>
                <div class="field brief">

                    <label>Movie Genre</label>
                    <select name="ugenre" id="genre" class="main-input">6
                        <option value="<%=movie.getGenre()%>"><%=movie.getGenre()%></option>
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
                    <br> </br><br> </br>
                    <label for="brief">Description</label>

                    <textarea name="udescription" id="description" class="main-input1"> <%= movie.getDescription()%> </textarea>
                </div>
                <span style="font-size: 12px; align-content: center; color: orange;"><%= (fieldempty != null) ? fieldempty : ""%></span>

                <div class="btn">
                    <input type="hidden" value="movie">
                    <button style="background-color:darkmagenta;" type ="submit">Update movie</button>
                </div>                
            </div>
        </div>
    </form>
    <br>
    <br>
    <footer>
        <p>SIUA 2023, UST, Sydney.
        <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
        <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
    </footer>  

</body>

</html>




