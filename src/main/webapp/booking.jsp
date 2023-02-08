<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="com.model.Customer"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Booking"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.BookingSqlDAO"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
                    <li><a href="testjsp.jsp">TEST JSP</a></li> 
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0">.com</span></h1>
        </header>
        <article class="main">

            <%
                Customer customer = (Customer) session.getAttribute("customer");
                int id = customer.getid();
                BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
                List<Booking> bookings = bookingSqlDAO.getBookings(id);
                int rows = bookings.size();
            %>
            <div class="tableDiv">

                <a href="step1.jsp" style="vertical-align: middle;text-decoration: none"><img src="image/add.png" style="margin-top:10%"></a>

                <table class="bookings" width="100%">
                    <caption>
                        You have <strong style="color:#e52323"><%= rows%></strong> bookings
                    </caption>
                    <colgroup>
                        <col id="poster">
                        <col id="name">
                        <col id="date">
                    </colgroup>
                    <tr>
                        <th scope="col">Poster</th>
                        <th scope="col">Movie Name</th>
                        <th scope="col">Booking Date</th>
                        <th scope="col">Edit</th>
                        <th scope="col">Delete</th>
                    </tr>

                    <% for (Booking booking : bookings) {%>
                    <p style="display: none"><%= booking.getBookingid()%></p>
                    <tr>
                        <td class="poster"><img src="<%= booking.getImgUrl()%>" width="50" height="50" alt="star"></td>
                        <td> <%= booking.getMovieName()%> </td>
                        <td><%= booking.getDate()%></td>
                        <%
                            int mbID = bookingSqlDAO.getmbID(bookingSqlDAO.getMovieID(booking.getMovieName()), booking.getBookingid());
                        %>
                        <td><a href="http://localhost:8080/group2/EditBooking.jsp?bID=<%= booking.getBookingid()%>&mID=<%=bookingSqlDAO.getMovieID(booking.getMovieName())%>&d=<%= booking.getDate()%>&mbID=<%=mbID%>"><img src="image/pencil-327.png"></a></td>
                            <td><a href="http://localhost:8080/group2/DeleteBooking.jsp?mbID=<%=mbID%>&mName=<%=booking.getMovieName()%>&d=<%= booking.getDate()%>"><img src="image/trash-can-10417.png"></a></td>
                    </tr>
                    <% }%>
                </table>
            </div>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>
    </body>
</html>
