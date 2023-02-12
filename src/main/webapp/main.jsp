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
                    <li><a href="aboutus.jsp">ABOUT THE APP</a></li>
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
            
            <h2 class="welcomemsg">Welcome, to your Dashboard <span style="color: purple; font-size: 1.5em;text-transform: capitalize"><%= customer.getName()%></span></h2>
        

            <aside class="adminAside">
                <div class="dashOptions"><a href="usersaccount.jsp"><img src="image/resume-9870.svg"><br>MY PROFILE</a></div>
                <div class="dashOptions"><a href="movies.jsp"><img src="image/video-833.svg"><br>My Movies</a></div>
                <div class="dashOptions"><a href="booking.jsp"><img src="image/click-mobile-phone-2406.svg"><br>MY BOOKINGS</a></div>
            </aside> 
            
            <% } else {
                Admin admin = (Admin) session.getAttribute("admin");%>
                <h2 class="welcomemsg">Welcome to Admin Dashboard <span style="color:purple; font-size: 1.5em;text-transform: capitalize"><%= admin.getName()%></span></h2>
         
            <aside class="customerAside">
                <div class="dashOptions"> <a href="usersaccount.jsp"><img src="image/resume-9870.svg"><br>MY PROFILE</a></div>
                <div class="dashOptions"><a href="customer.jsp"><img src="image/client-5253.svg"><br>CUSTOMERS</a></div>
                <div class="dashOptions"><a href="movies.jsp"><img src="image/video-833.svg"><br>MOVIES</a></div>
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