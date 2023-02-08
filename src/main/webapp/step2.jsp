<%-- 
    Document   : step2
    Created on : 2 Feb. 2023, 5:47:53 pm
    Author     : 236347
--%>

<%@page import="com.model.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.BookingSqlDAO"%>
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
                    <li><a href="main.jsp">Dashboard</a></li>
                     <li><a href="booking.jsp">BOOKINGS</a></li>
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0">.com</span></h1>
        </header>
        <article class="main">
            <div class="tableDiv">  
               
                <%
                    String added = (String) session.getAttribute("added");
                %>
                <%
                    int bID = (Integer) session.getAttribute("bID");
                    BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
                    List<String> movieNames = bookingSqlDAO.getMovies();
                    Customer customer = (Customer) session.getAttribute("customer");
                    int customerid = customer.getid();
                %>
                
                <p>Pick your movie</p>
                <form id="form1" method="post" action="/group2/Step3Servlet">
                    <fieldset style="border: 1px solid black">
                        <legend style="color:#e52323"><%= (added != null) ? added : ""%></legend>
                        <select name="movie">
                            <% for (String movieName : movieNames) {%>
                            <option value="<%=movieName%>"><%=movieName%></option>
                            <%}%>
                        </select>
                        <input type="submit" value="ADD">
                    </fieldset>
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
