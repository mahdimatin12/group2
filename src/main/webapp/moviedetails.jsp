<%-- 
    Document   : moviedetails
    Created on : 30-Jan-2023, 10:59:32 PM
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
        <title>Movie</title>
        <style>
           
            form input {
                border: none;
                background-color: transparent;

            }
            #div3 {
                position: relative;
                float: right;


            }
            #div3 iframe {
                width: 410px;
                height: 270px;
                margin: 20px;
            }
            #div1 img {
               margin: 0;
            }
            #upbtn,#delbtn{              
            width: 10%;
            height: 36px;
            background-color:darkmagenta;
            color: white;
            padding: 0.6rem;
            border: none;
            border-radius: 4px;
            cursor: pointer; 
            text-decoration: none;
            }
            
            
            
        </style>

    </head>
    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>                                     
                    <li><a href="main.jsp">Dashboard</a></li>
                    <li><a href="movies.jsp">MovieList</a></li>
                    <li><a href="/group2/LogoutServlet">Logout</a></li>
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>

        <%
            MovieSqlDAO movieSqlDAO = (MovieSqlDAO) session.getAttribute("movieSqlDAO");
            Movie movie = null;
            movie = (Movie) session.getAttribute("movie");
            Admin admin = (Admin) session.getAttribute("admin");
            Customer customer = (Customer) session.getAttribute("customer");
        %>
        <article class="main">
            <div class="loginContent" style="height:100%; width: 50%; margin-left: 25%;">


                <div style="margin: auto;">
                    <div id="div3">
                        <iframe name="vidurl"src="<%=movie.getVidUrl()%>"></iframe>
                    </div>
                    <br>
                    <div id="div2" style="top:0">
                        <img name="imgurl" src="<%=movie.getImgUrl()%>" width="300px" height="270px">

                    </div>
                    <div id="div1" style="padding:15px; text-align: center; text-justify: auto;">
                        <p><%=movie.getName()%></p>
                        <p><%=movie.getGenre()%>
                            <span> <p><%=movie.getRuntime()%></p></span>
                        </p>
                        <p><%= movie.getDescription()%></p>
                        

                    </div>

                    <div id="div4">
                        <% if (customer != null) {%>
                        <form action="moviebookingpage.jsp">                
                            <input id="delbtn"type="submit" value="Book Now">
                        </form>
                        <% }%>
                        <% if (admin != null) {%>
                        
                            <a id="upbtn" href="movieupdate.jsp"> Update</a>
                       
                        <% }%>

                        <% if (admin != null) {%>
                        
                        <a id="delbtn" href="/group2/MovieDeleteServlet" onclick="return confirm('Are you sure you want to delete this movie?');">
                        Delete
                        </a>
                        <% }%> 

                    </div>
                    
                </div>
            </div>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer> 

    </body>



</html>



