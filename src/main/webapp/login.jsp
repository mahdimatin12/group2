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
                        <li><a href="index.jsp">Home</a></li>
                    </ul>
                </nav>

                <span>&#9654</span>
                <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
            </header>
            <div class="contentWrapper">
                <div class="columnWrapper">
                    <!-- main content goes here -->
                    <article class="main">
                        <div class="loginContent">
                            <%
                                String exist = (String) session.getAttribute("error");
                                session.removeAttribute("error");
                            %>
                            <h1>Sign In As <span style="font-size: 10px; color: orange;"><%= (exist != null) ? exist : ""%></span></h1>


                            <button id="p1"><img src="image/admin-9584.svg" style="height: 30px;width: 30px;">Admin</button>
                            <button id="p2"><img src="image/person-295.svg" style="height: 30px;width: 30px;">Customer</button>

                            <form id="form1" class="admin" action="/group2/AdminServlet" method="POST">

                                <input name="email" placeholder="admin email" type="text">
                                <input name="password" placeholder="admin password" type="password">                               
                                <input type="submit" value="SIGN IN" style="cursor: pointer">

                                  <p style="color:black ; font-size: 10px;margin-top: 20px;margin-left:0%; ">  Not Register? <a href="register.jsp"> click here</a></p>
                            </form>

                            <form id="form2" class="admin" action="/group2/CustomerServlet" method="POST">
                                <input name="email" placeholder="customer email" type="text">
                                <input name="password" placeholder="customer password" type="password">                               
                                <input type="submit" value="SIGN IN" style="cursor: pointer;"> 
                                
                                 <p style="color:black ; font-size: 10px;margin-top: 20px;margin-left:0%; "> Not Register? <a href="register.jsp"> click here</a></p>
                            </form>
                             
                        </div>

                        <script>

                            const a = document.getElementById('form1');
                            const b = document.getElementById('form2');
                            const p1 = document.getElementById('p1');
                            const p2 = document.getElementById('p2');

                            function third() {
                                a.style.display = 'block';
                                b.style.display = 'none';

                            }
                            function fourth() {
                                a.style.display = 'none';
                                b.style.display = 'block';
                            }
                            p1.addEventListener('click', third, false);
                            p2.addEventListener('click', fourth, false);
                        </script>
                    </article>


                </div>
            </div>
            <footer>
                <p>SIUA 2023, UST, Sydney.
                <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
                <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
            </footer>
        </div>

    </body>
</html>
