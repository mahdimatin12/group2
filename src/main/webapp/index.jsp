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

        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="register.jsp">SIGN UP</a></li>                    
                    <li><a href="login.jsp">LOGIN</a></li>
                    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>


        <!-- main content goes here -->
        <article class="main">

            <script src="js/main.js"></script>
            <h2></h2>
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

        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>

        <jsp:include page="/InitServlet" flush="true"/>
    </body>
</html>
