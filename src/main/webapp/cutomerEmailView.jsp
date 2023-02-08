

<%@page import="com.model.Customer"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Email View</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>
    <main>
        <article class="main">
            <div class="content">
                <form class="viewForm" action="/group2/CustomerEmailViewServlet" method="POST">
                </form>
                <h1>Customer</h1>

                <%
                    request.setAttribute("email", null);
                    request.removeAttribute("email");
                %>
               
                
                <%
                    Customer customer = (Customer) session.getAttribute("customer");
                    CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                    customer = customerSqlDAO.getCustomer("email");


                %>

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
                <footer>
                    <p>SIUA 2023, UST, Sydney.
                    <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
                    <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
                </footer>
                </body>
                </html>

