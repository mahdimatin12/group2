
<%@page import="com.model.Customer"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin View</title>
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body>
        <nav class="clear">
            <ul>
              <li><a class="button" href="index.jsp">Home</a></li>
              <li><a class="button" href="/group2/LogoutServlet">Logout</a></li> 
              <li><a class="button" href="/group2/createCustomer.jsp"> Register </a></li>
              <li><a class="button" href="#"> Update </a></li>
              <li><a class="button" href="#"> delete </a></li>
            </ul>
        </nav>
        
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Dob</th>
                    <th>Phone</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <%
                    request.setAttribute("email", null);
                    request.removeAttribute("email");
                %>
                <%
                    Customer customer = (Customer) session.getAttribute("customer");
                    CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                    customer = customerSqlDAO.getCustomer("id");

                %>

                <tr>
                    <td>${customer.id}</td>
                    <td>${customer.name}</td>
                    <td>${customer.gender}</td>
                    <td>${customer.dob}</td>
                    <td>${customer.phone}</td>
                    <td>${customer.email}</td>
                    
                </tr>                

            </tbody>
        </table>
    </body>

</html>
