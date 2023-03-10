
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link href="css/styles.css" rel="stylesheet">
        <link href="css/ramya.css" rel="stylesheet">
        <style>
            .button11{

                font-size: 15px;
                font-weight: bold;  
                width: 130px;
                padding: 10px;
                text-align: center;
                text-decoration: none;   
                color: white;
                border-radius: 5px;
                cursor: pointer;
                background-color:slategray;

            }

            .button11:hover {
                background-color: lightblue;
                color: black;
            }

            th td tr{
                color: black;
            }
        </style>
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
    <%
        CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
        Customer customer = (Customer) session.getAttribute("customer");
        customer = customerSqlDAO.getCustomer(customer.getEmail());
    %>
    <%
        String nameError = (String) session.getAttribute("nameError");
        session.removeAttribute("nameError");
        String genderError = (String) session.getAttribute("genderError");
        session.removeAttribute("genderError");
        String dobError = (String) session.getAttribute("dobError");
        session.removeAttribute("dobError");
        String phoneError = (String) session.getAttribute("phoneError");
        session.removeAttribute("phoneError");
        String passError = (String) session.getAttribute("passError");
        session.removeAttribute("passError");
        String update = (String) session.getAttribute("update");
        session.removeAttribute("update");
    %>
    <article class="main">
        <div>
            <form id="form1" style="width: 40%; margin-left:25%; margin-top:3%;" action="/group2/updateCustomerServlet" method="POST">
                <table class="fl-table">
                    <thead>         
                    <caption style=" background-color: purple; align-content: center; font-weight:bold; height:30px;"> Edit Customer &ensp;&ensp;<span class="message"><%= (update != null) ? update : ""%><%= (dobError != null) ? dobError : ""%></span></caption>
                    <th><tr><td style="color:black;">ID: </td><td><input type="text" name="ID" value="<%= customer.getid()%>" readonly="true" /></td></tr></th>
                    <th><tr><td style="color:black;">Name: </td><td><input type="text" name="name"  value="<%= (nameError != null) ? nameError : customer.getName()%>"/></td></tr></th>
                    <th><tr><td style="color:black;">Gender: </td><td><input name="gender" value="<%= (genderError != null) ? genderError : customer.getGender()%>" /></td></tr></th>                
                    <th><tr><td style="color:black;">DOB: </td><td><input type="date" name="dob" value="<%= customer.getDob()%>"/></td></tr></th>
                    <th><tr><td style="color:black;">Mobile Number: </td><td><input type="text" name="phone" value="<%= (phoneError != null) ? phoneError : customer.getPhone()%>"/></td></tr></th>
                    <th><tr><td style="color:black;">Email: </td><td><input type="text" name="email" value="<%= customer.getEmail()%>" readonly="true"/></td></tr></th>
                    <th><tr><td style="color:black;">Password: </td><td><input type="text" id="password" name="password" value="<%= (passError != null) ? passError : customer.getPassword()%>" /></td></tr></th>
                    <tr><input type="hidden" name="submitted" value="submitted"></tr>                
                    </thead>
                    <tr>
                        <td> <input style=" background-color:slategray; font-weight:bold;" class="button11" type="submit" value="Update" /> </td>
                        <td>
                            <a style=" width: 120px;   padding: 7px" class="button11" href="/group2/CustomerDeleteServlet">Delete</a>
                        </td>
                    </tr>
                </table>
            </form>
        </div> 
    </article>
    <footer>
        <p>SIUA 2023, UST, Sydney.
        <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
        <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
    </footer>
</body>
</html>


