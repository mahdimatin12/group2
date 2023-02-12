
<%@page import="com.model.Customer"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin View</title>
        <link rel="stylesheet" href="css/styles.css">
         <link href="css/ramya.css" rel="stylesheet">
    </head>
    <header>
        <nav class="clear">
            <ul>
                <li><a class="button" href="customer.jsp">Customers List</a></li>
                <li><a class="button" href="main.jsp">Home</a></li> 
            </ul>
        </nav>
        <span>&#9654</span>
        <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
    </header>
    <article>
        <div style="margin-top: 5%">
            
            <%
                Customer customer = (Customer) session.getAttribute("customer");
                CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                customer = customerSqlDAO.getCustomer("id");
            %>
            <table class="fl-table">
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
        </div>
    </article>
    <footer>
        <p>SIUA 2023, UST, Sydney.
        <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
        <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
    </footer>
</body>
</html>
