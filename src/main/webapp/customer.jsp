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
        <link href="css/ramya.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="clear">
                <ul>
                    
                    <li><a href="register.jsp">SIGN UP</a></li>                    
                    <li><a href="login.jsp">LOGIN</a></li>
                    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
        <%
            request.setAttribute("email", null);
            request.removeAttribute("email");
            String error = (String) session.getAttribute("error");
            session.removeAttribute("error");
        %>

        <article class="main">
            <div style="margin-top: 5%">

                <form id="form1" style="width: 25%; margin-left:25%" action="/group2/AdminSearchServlets" method="POST">
                    <input name="id" type="text" id="id" placeholder="<%= (error != null) ? error : "Search By ID..."%>">
                    <input id="searchbtn" type="submit" value="Search">

                </form>
                    <br>
                <table  class="fl-table">
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
                    <%
                        request.setAttribute("email", null);
                        request.removeAttribute("email");
                    %>
                    <%
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
                        <td>
                            <a href="ShowCustomerInfoServlet?emailView=<%= customer.getEmail()%>"><%= customer.getEmail()%>></a>
                        </td>                    
                    </tr>                
                    <%}%>
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


