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
        <title>JSP Page</title>
    </head>
    <body>

        <%
            String moviemsg = (String) session.getAttribute("moviemsg");
        %>
        <h1> <span style="font-size: 10px; color: orange;"><%= (moviemsg != null) ? moviemsg : ""%></span></h1>
        <form class="form-horizontal"action="/group2/MovieAddServlet" method="POST">
            <fieldset>

                <!-- Form Name -->
                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="textinput">Title</label>  
                    <div class="col-md-4">
                        <input id="namee" name="name" type="text" placeholder="Movie name/title" class="form-control input-md">

                    </div>
                </div>

                <!-- Button Drop Down -->
                <label for="genre">Movie genre/category:</label>
                <select name="genre" id="genre">
                    <option value="Action">Action</option>
                    <option value="Adventure">Adventure</option>
                    <option value="Comdey" disabled>Comedy</option>
                    <option value="Fantasy" selected>Fantasy</option>
                </select>


                <!-- Text area -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="description">Summary</label>
                    <div class="col-md-4">                     
                        <textarea class="form-control" id="description" name="description">Summary, Casting...</textarea>
                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="year">Year</label>  
                    <div class="col-md-4">
                        <input id="year" name="year" type="text" placeholder="Release year" class="form-control input-md">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="runtime">Runtime</label>  
                    <div class="col-md-4">
                        <input id="runtime" name="runtime" type="text" placeholder="eg:2h 30m" class="form-control input-md">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="imgurl">Image URL</label>  
                    <div class="col-md-4">
                        <input id="imgurl" name="imgurl" type="text" placeholder="Add a image URL link" class="form-control input-md">

                    </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="vidurl">Trailer URL</label>  
                    <div class="col-md-4">
                        <input id="vidurl" name="vidurl" type="text" placeholder="Add a video URL link" class="form-control input-md">

                    </div>
                </div>

                <!-- Button (Double) -->
                <div class="form-group">
                    <label class="col-md-4 control-label" for="add">Add</label>
                    <div class="col-md-8">
                        <button id="add" name="add" class="btn btn-success">Add</button>
                        <button id="cancel" name="cancel" class="btn btn-default">Cancel</button>
                    </div>
                </div>
            </fieldset>
        </form>

    </body>
</html>
