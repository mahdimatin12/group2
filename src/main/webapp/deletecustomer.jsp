<%-- 
    Document   : delete
    Created on : Dec 7, 2022, 10:57:56 AM
    Author     : George
--%>

<%@page import="com.model.dao.AdminSqlDAO"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customers"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Page</title>
        <link rel="stylesheet" href="css/layout.css"/>
        <script type="text/javascript" src="js/index.js"></script>
    </head>
    <body>
        <%!
            Customer costumer;
        %>
       
        <%
            CustomerSqlDAO costumerSqlDAO = (CustomerSqlDAO) session.getAttribute("costumerSqlDAO");
                        
            String emailView = (String) session.getAttribute("emailView");
            if (emailView != null) {
                costumer = costumerSqlDAO.getCustomer(emailView);
            }else{
                costumer = (Customer) session.getAttribute("costumer");
            }

            if (costumer != null) {
                costumerSqlDAO.delete(costumer.getid());
        %>
                <h2><%= costumer.getName()%> record has been deleted!</h2>
            <%}%>
        <% session.invalidate();%>
        <p class="message">You have been logged out click <a href="index.jsp">here </a> to go back home</p>   
    </body>
</html>
