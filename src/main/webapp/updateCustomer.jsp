

<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
    </head>
    <body>
         <%
            CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");            
            Customer customer = (Customer) session.getAttribute("customer");       
//            String emailView = request.getParameter("emailView");
//            String submitted = request.getParameter("submitted");
           
        %>
    </body>
<!--    <div>
            <form method="POST" action="/group2/CustomerAccountServlet">
                <table class="table">
                    <caption>Edit Customer <span class="message"><%= //(submitted != null) ? "Update is Successful" : ""%></span></caption>
                    <tr><td>ID: </td><td><input type="text" name="ID" value="<%= //customer.getid()%>" readonly="true" /></td></tr>
                    <tr><td>Name: </td><td><input type="text" name="name" value="<%= //customer.getName()%>" /></td></tr>
                    <tr><td>Gender: </td><td><input name="gender" value="<%= //customer.getGender()%>" /></td></tr>
                    <tr><td>DOB: </td><td><input type="date" name="dob" value="<%= //customer.getDob()%>"/></td></tr>
                    <tr><td>Mobile Number: </td><td><input type="text" name="phone" value="<%= //customer.getPhone()%>" /></td></tr>
                    <tr><td>Email: </td><td><input type="text" name="email" value="<%= //customer.getEmail()%>" readonly="true"/></td></tr>
                    <tr><td>Password: </td><td><input type="password" name="password" value="<%= //customer.getPassword()%>" /></td></tr>
                    
                    <tr><input type="hidden" name="submitted" value="submitted"></tr>
                    <tr>
                        <td>
                            <% //if (emailView != null) { %>
                            <a class="button" href="customerView.jsp">Accounts</a> 
                            <%} else { %>
                            <a class="button" href="main.jsp">Dashboard</a>
                            <%}%>
                        </td>
                        <td>
                            <input class="button" type="submit" value="Update" /> 
                            <a class="button" href="/group2/DeleteCustomerServlet">Delete</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>        
        <div id="clock" class="footer"></div>-->
    </body>
</html>

