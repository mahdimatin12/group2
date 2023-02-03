
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
        <header>
            <div class="Navbar">
                <nav>
                    <ul class="nav">
                        <li><a class="button" href="index.jsp">Home</a></li>
                        <li><a class="button" href="/group2/LogoutServlet">Logout</a></li> 
                        <li><a class="button" href="/group2/createCustomer.jsp"> Register </a></li>
                        <li><a class="button" href="#"> Update </a></li>
                        <li><a class="button" href="#"> delete </a></li>
                    </ul>
                </nav>
            </div>
        </header>
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
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </body>

</html>
