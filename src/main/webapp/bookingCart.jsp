<%-- 
    Document   : bookingCart
    Created on : 2 Feb. 2023, 3:12:38 pm
    Author     : 236347
--%>

<%@page import="com.model.Booking"%>
<%@page import="com.model.Bookings"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table>
            
        
        <%
           
            List<Booking> bookings = (List<Booking>)session.getAttribute("bookings");
            %>
            <tr>  
                <%
            for (Booking booking:bookings) {
                System.out.println(booking);
            }
             %>
            </tr>
        %>
        </table>
    </body>
</html>
