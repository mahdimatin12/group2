<%@page import="com.model.Admin"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">       
        <title>Welcome</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>

    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>                   
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
        <!-- main content goes here -->
        <article class="main">
            <%
                Customer customer = (Customer) session.getAttribute("customer");
                if (customer != null) {
            %>  
            
            <h2 class="welcomemsg">Welcome, to your Dashboard <%= customer.getName()%>!</h2>
        

            <aside class="adminAside">
                <a href="usersaccount.jsp"><img src="image/resume-9870.svg"><br>MY PROFILE</a>
                <a href="movies.jsp"><img src="image/video-833.svg"><br>Movies</a>
                <a href="booking.jsp"><img src="image/click-mobile-phone-2406.svg"><br>MY BOOKINGS</a>
            </aside> 
            
            <% } else {
                Admin admin = (Admin) session.getAttribute("admin");%>
            <h2 class="welcomemsg">Welcome to Admin Dashboard <%= admin.getName()%>!</h2>
         
            <aside class="customerAside">
                <a href="usersaccount.jsp"><img src="image/resume-9870.svg"><br>MY PROFILE</a>
                <a href="#"><img src="image/client-5253.svg"><br>CUSTOMERS</a>
                <a href="movies.jsp"><img src="image/video-833.svg"><br>MOVIES</a>
            </aside>
            
            <% }%> 
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.</p>
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </body>
</html>