<%-- 
    Document   : login
    Created on : 24 Jan. 2023, 8:33:55 pm
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
        <div class="pageWrapper">
            <header>
                <nav class="clear">
                    <ul>
                        <li><a href="#">About The App</a></li>
                        <li><a href="#">Task One</a></li>
                        <li><a href="#">Task Two</a></li>
                        <li><a href="#">Task Three</a></li>
                        <li><a href="index.jsp">Home</a></li>
                    </ul>
                </nav>

                <span>&#9654</span>
                <h1>mymovies.com</h1>
            </header>
            <div class="contentWrapper">
                <div class="columnWrapper">
                    <!-- main content goes here -->
                    <article class="main">
                        <div class="loginContent">
                        <h1>I want to login as: </h1><br>
                        <p id="p1">Admin</p>
                        <p id="p2">Customer</p>

                        <form id="form1" class="admin" action="/group2/AdminServlet" method="POST">
                            <input name="email" placeholder="email" type="text">
                            <input name="password" placeholder="password" type="password"> 
                            <input type="hidden" value="admin">
                            <input type="submit" value="LOGIN" style="cursor: pointer">            
                        </form>
                        </div>

                        <script>

                            const a = document.getElementById('form1');
                            const b = document.getElementById('customer');
                            const p1 = document.getElementById('p1');
                            const p2 = document.getElementById('p2');

                            function third() {
                                a.classList.toggle('admin');
                            }
                            p1.addEventListener('click', third, false);
                        </script>
                    </article>


                </div>
            </div>
            <footer>
                <p>SIUA 2023, UST, Sydney.
                <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
                <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
            </footer>
        </div>

    </body>
</html>
