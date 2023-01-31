<%-- 
    Document   : delete
    Created on : Dec 7, 2022, 10:57:56 AM
    Author     : George
--%>

<%@page import="com.model.dao.AdminSqlDAO"%>
<%@page import="com.model.dao.AdminSqlDAO"%>
<%@page import="com.model.Admins"%>
<%@page import="com.model.Admin"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Delete Page</title>
       
    </head>
    <body>
        <%!
            Admin admin;
        %>
       
        <%
            AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
                        
            String emailView = (String) session.getAttribute("emailView");
            if (emailView != null) {
                admin = adminSqlDAO.getAdmin(emailView);
            }else{
                admin = (Admin) session.getAttribute("admin");
            }

            if (admin != null) {
                adminSqlDAO.delete(admin.getid());
        %>
                <h2><%= admin.getName()%> record has been deleted!</h2>
            <%}%>
        <% session.invalidate();%>
        <p class="message">You have been logged out click <a href="index.jsp">here </a> to go back home</p>   
    </body>
</html>
