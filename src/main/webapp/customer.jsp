<%@page import="com.model.Customers"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer View</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a class="button" href="index.jsp">Home</a></li>                    
                
                </ul>
            </nav>
        </header>
        <main>
            <article class="main">
                <div class="content">
                    <form class="searchForm" action="/group2/AdminSearchServlets" method="POST">
                        <input name="id" type="text" id="id" placeholder="search...">
                        <input id="searchbtn" type="submit" value="search">

                    </form>

                    <h1>Customers</h1>
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
                                //Customer customer = (Customer) session.getAttribute("customer");
                                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                                List<Customer> customers = customerSqlDAO.getCustomers();
                                for (Customer customer : customers) {

                            %>

                            <tr>
                                <td><%= customer.getid()%></td>
                                <td><%= customer.getName()%></td>
                                <td><%= customer.getGender()%></td>
                                <td><%= customer.getDob()%></td>
                                <td><%= customer.getPhone()%></td>
                                <td><a href="<%= customer.getEmail()%>"><%= customer.getEmail()%></a></td>
                            </tr>                
                            <% }%>
                        </tbody>
                    </table>
                    </body>
                    </html>


