<%-- 
    Document   : index
    Created on : 22 Jan. 2023, 9:07:58 pm
    Author     : 236347
--%>

<%@page import="java.util.Random"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Movies: Assessment 3</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <%
            if (session.isNew()) {                             
                String userID = "" + (new Random().nextInt(999));
                session.setAttribute("userid", userID);
                Integer count = new Integer(0);
                session.setAttribute("counter", count);
            }
        %>
        <div class="pageWrapper">
            <header>
                <nav class="clear">
                    <ul>
                        <li><a href="#">About The App</a></li>
                        <li><a href="#">Task One</a></li>
                        <li><a href="#">Task Two</a></li>
                        <li><a href="#">Task Three</a></li>
                        <li><a href="login.jsp">login</a></li>
                    </ul>
                </nav>
                
                <span>&#9654</span>
                <h1>mymovies.com</h1>
            </header>
            <div class="contentWrapper">
                <div class="columnWrapper">
                    <!-- main content goes here -->
                    <article class="main">

                        <script src="js/main.js"></script>
                        <h2>Top Movies</h2>
                        <div id="slider">

                            <ul id="slideWrap"> 
                                <li><img src="image/Avatar.jpg"></li>
                                <li><img src="image/Luck.jpg"></li>
                                <li><img src="image/Spiderman.jpg"></li>

                            </ul>
                            <a id="rev"href="#">&#8810;</a>
                            <a id="next" href="#">&#8811;</a>
                        </div>

                    </article>


                </div>
            </div>
            <footer>
                <p>SIUA 2023, UST, Sydney.
                <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
                <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
            </footer>
        </div>
         <jsp:include page="/InitServlet" flush="true"/>
    </body>
</html>
