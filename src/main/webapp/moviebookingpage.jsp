<%-- 
    Document   : moviebookingpage
    Created on : 9 Feb. 2023, 11:44:42 pm
    Author     : 236347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>movie booking page</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <%
            String empty = (String) session.getAttribute("empty");
            int moviesid = (Integer)session.getAttribute("movieid");
            

        %>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="main.jsp">Dashboard</a></li>
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0">.com</span></h1>
        </header>
        <article class="main">
            <div class="tableDiv" style="width:20%">

                <form id="form1" method="POST" action="/group2/MoviePageStep1Servlet"> 
                    <fieldset style="border: 1px solid black">
                        <legend><%=(empty != null) ? empty : ""%></legend>
                        <input type="date" name="date">                       
                        <input type="submit" value="ADD">
                    </fieldset>
                </form>                        
            </div>
            <% empty = "";%>

        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>
    </body>
</html>
