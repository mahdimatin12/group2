<%-- 
    Document   : step1
    Created on : 2 Feb. 2023, 5:02:11 pm
    Author     : 236347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Movies: Assessment 3</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="main.jsp">DASHBOARD</a></li> 
                    <li><a href="main.jsp">BOOKINGS</a></li> 
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0">.com</span></h1>
        </header>
        <article class="main">
            <div class="tableDiv">  
                <form id="form1" method="post" action="/group2/Step1Servlet">              
                    <lable style="color:black" for="date">Pick a Date</lable>
                    <input type="date" name="date">             
                    <input type="submit" value="ADD">
                </form>
            </div>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>
    </body>
</html>
