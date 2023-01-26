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
        <div class="pageWrapper">
            <header>
                <nav class="clear">
                    <ul>
                        <li><a href="/group2/LogoutServlet">logout</a></li>
                    </ul>
                </nav>
                 <span>&#9654</span>
                <h1>mymovies.com</h1>
            </header>
            <div class="contentWrapper">
                <div class="columnWrapper">
                    <!-- main content goes here -->
                    <article class="main">

                        <aside>
                            <%
                                Customer customer = (Customer) session.getAttribute("customer");
                                if (customer != null) {
                            %>  
                            <h2>Welcome, to your Dashboard <%= customer.getName()%>!</h2>
                            <a href="#">MY PROFILE</a>
                            <a href="#">Movies</a>
                            <a href="#">MY BOOKINGS</a>
                            
                            <% } else {
                            Admin admin = (Admin) session.getAttribute("admin");%>
                            <h2>Welcome to Admin Dashboard <%= admin.getName()%>!</h2>
                            
                            <a href="#">MY PROFILE</a>
                            <a href="#">CUSTOMERS</a>
                            <a href="#">MOVIES</a>
                            
                            <% }%> 
                        </aside>
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
