<%-- 
    Document   : testjsp
    Created on : 6 Feb. 2023, 10:24:50 am
    Author     : 236347
--%>


<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.model.dao.BookingSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form method="get" action="testjsp.jsp">
            <input type="date" name="inputDate">
            <input type="submit" value="Send">
        </form>
        <%
            String date1 = request.getParameter("inputDate");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date2 = sdf.parse(date1);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();
            System.out.println(dtf.format(now));

        %>
        <h1><%=date1%></h1>
        <h2><%=date2%></h2>
        <h3><%%></h3>

    </body>
</html>
