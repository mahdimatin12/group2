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
        <title>Edit Booking</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <body>
        <%
            BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
            List<String> movieNames = bookingSqlDAO.getMovies();
            String msg1 = (String) session.getAttribute("msg1");
             String msg2 = (String) session.getAttribute("msg2");

            int bID = 0;
            int mID = 0;
            int mbID = 0;
            String date = null;

            try {
                bID = Integer.parseInt(request.getParameter("bID"));
                mID = Integer.parseInt(request.getParameter("mID"));
                mbID = Integer.parseInt(request.getParameter("mbID"));
                date = request.getParameter("d");
            } catch (NumberFormatException nfe) {
                System.out.println("EditBooking.jsp Line-31--> "+nfe);
            }
        %>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="booking.jsp">BOOKINGS</a></li>
                    <li><a href="main.jsp">DASHBOARD</a></li>    
                    <li><a href="/group2/LogoutServlet">LOGOUT</a></li>    
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0">.com</span></h1>
        </header>
        <article class="main">
            <div class="tableDiv"> 
                <table class="bookings" width="100%">
                    <caption>
                        <%= (msg2 != null) ? msg2 : ""%>
                    </caption>
                    <colgroup>                        
                        <col id="name">
                        <col id="date">
                    </colgroup>
                    <tr>                        
                        <th scope="col">Movie Name</th>
                        <th scope="col">Booking Date</th>                      
                    </tr>                   
                    <tr>                       
                        <td> <%= bookingSqlDAO.getMovieName(mID)%> </td>
                        <td><%= date%></td>                      
                    </tr>                   
                </table>

                <form id="form1" method="post" action="/group2/EditBookingServlet">
                    <fieldset>
                        <legend>
                            <%= (msg1 != null) ? msg1 : ""%>
                        </legend>
                        <select name="movie">
                            <option value="">--Pick another movie--</option>
                            <% for (String movieName : movieNames) {%>
                            <option value="<%=movieName%>"><%=movieName%></option> 

                            <%}%>
                        </select>
                        <input type="date" name="date">
                        <input type="hidden" value="<%=mID%>" name="mID">
                        <input type="hidden" value="<%=bID%>" name="bID">
                        <input type="hidden" value="<%=mbID%>" name="mbID">
                        <input type="submit" value="UPDATE">
                    </fieldset>

                </form>
                </table>
            </div>
            <%
                session.removeAttribute("msg1");
                session.removeAttribute("msg2");
                
            %>    

        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>

    </body>
</html>
