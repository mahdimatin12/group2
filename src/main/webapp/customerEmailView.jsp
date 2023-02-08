
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Email View</title>

        <link href="css/styles.css" rel="stylesheet">
        <link href="css/ramya.css" rel="stylesheet">
    </head>
    <header>
        <nav class="clear">
            <ul>

                <li><a class="button" href="customer.jsp">Customers List</a></li>
                <li><a class="button" href="/group2/LogoutServlet">Logout</a></li>

            </ul>
        </nav>

        <span>&#9654</span>
        <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
    </header>

    <article class="main">
        <div style="margin-left:20%" class="content">
            <form id="form1" action="/group2/CustomerEmailViewServlet" method="POST">
            </form>

            <%
                Customer customer = (Customer) session.getAttribute("customer");
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                customer = customerSqlDAO.getCustomer(customer.getEmail());

            %>

            <table class="fl-table">
                <thead>
                    <tr>             
                <th>Edit Account</th>
                <th></th>
                </tr>
                </thead>
                <tbody>

                    <tr><td>ID:</td><td><%= customer.getid()%></td></tr>
                    <tr><td>Name</td><td><%= customer.getName()%></td></tr>
                    <tr><td> Gender:<td><%= customer.getGender()%></td></tr>
                    <tr><td> DOB:<td><%= customer.getDob()%></td></tr>
                    <tr><td>Phone:</td>  <td><%= customer.getPhone()%></td></tr>
                    <tr><td>Email:  <td><a href="ShowCustomerInfoServlet?emailView=<%= customer.getEmail()%>"><%= customer.getEmail()%>></a> </td> </tr> 
                    <tr><td> <a class="button" href="/group2/updateCustomer.jsp"> Update </a></td><td>    <a class="button" href="/group2/CustomerDeleteServlet"> delete </a></td></tr>

                </tbody>
            </table> 
        </div>
    </article>
    <footer>
        <p>SIUA 2023, UST, Sydney.
        <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
        <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
    </footer>
</body>
</html>


