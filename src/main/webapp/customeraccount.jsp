<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customers"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="css/style.css"/>
        <script type="text/javascript" src="js/time.js"></script>

        <style>
            table {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                width: 100%;
                margin-top: 25px;
                border-collapse: collapse;
            }
            caption {
                text-align: right;
                font-size: .85em;
                margin-bottom: 10px;
            }
            th, td {
                font-size: 1.1em;
                border: 1px solid #DDB575;
                padding: 3px 7px 2px 7px;
            }
            th {
                text-transform:uppercase;
                text-align: left;
                padding-top: 5px;
                padding-bottom: 4px;
                background: rgb(229,76,16);
                background: linear-gradient(to bottom, rgb(229,76,16), rgb(173,54,8));
                color: white;
            }
            tr:nth-of-type(even){
                background-color: rgba(255,255,255,.1);
            }
            tr:nth-of-type(odd){
                background-color: rgba(229,76,16,.1);
            }

        </style>
    </head>
    <body onload="startTime()"> 


        <main>
            <article>
                <div class="content">

                    <%
                        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
                        Customer customer = (Customer) session.getAttribute("customer");
                        String emailView = request.getParameter("emailView");
                        String submitted = request.getParameter("submitted");

                    %>
                    <div style="margin: auto;">
                        <form style="width:100%" method="POST" action="/group2/CustomerAccountServlet">
                            <table class="table" style="width: 70%;">
                                <caption>Edit Customer <span class="message"><%= (submitted != null) ? "Update is Successful" : ""%></span></caption>
                                <tr><td>ID: </td><td><input type="text" name="ID" value="<%= customer.getid()%>" readonly="true" /></td></tr>
                                <tr><td>Name: </td><td><input type="text" name="name" value="<%= customer.getName()%>" /></td></tr>
                                <tr><td>Gender: </td><td><input type="text" name="radio" value="<%= customer.getGender()%>" /></td></tr>
                                <tr><td>DOB: </td><td><input type="text" name="dob" value="<%= customer.getDob()%>" /></td></tr>
                                <tr><td>Mobile Number: </td><td><input type="phone" name="name" value="<%= customer.getPhone()%>" /></td></tr>
                                <tr><td>Email: </td><td><input type="text" name="email" value="<%= customer.getEmail()%>" readonly="true"/></td></tr>
                                <tr><td>Password: </td><td><input type="password" name="password" value="<%= customer.getPassword()%>" /></td></tr>

                                <tr><input type="hidden" name="submitted" value="submitted"></tr>
                                <tr>
                                    <td>
                                        <% if (emailView != null) { %>
                                        <a id="cancelbtn" href="#">Accounts</a> 
                                        <%} else { %>
                                        <a id="dashboardbtn" href="main.jsp">Dashboard</a>
                                        <%}%>
                                    </td>
                                    <td>
                                        <input 10px;" id="cancelbtn" type="submit" value="Update" /> 
                                        <a id="cancelbtn" href="/group2/DeleteCustomerServlet">Delete</a>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div> 
                </div>
            </article>
        </main>
        <footer>
            <p id="clock"></p>
        </footer>
    </body>
</html>

