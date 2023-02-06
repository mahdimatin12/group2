
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
            String emailView = request.getParameter("emailView");
            

        %>
        <% 
            String nameError = (String) session.getAttribute("nameError");
            session.removeAttribute("nameError");
            String phoneError = (String) session.getAttribute("phoneError");
            session.removeAttribute("phoneError");           
            String passError = (String) session.getAttribute("passError");
            session.removeAttribute("passError");
            String genderError = (String) session.getAttribute("genderError");
            session.removeAttribute("genderError");
            String error = (String) session.getAttribute("error");
            session.removeAttribute("error");
            String update=(String)session.getAttribute("update");
            session.removeAttribute("update");
        %>
    </body>
    <div>
        <form method="POST" action="/group2/updateCustomerServlet">
            <table class="table">
                <caption>Edit Customer <span class="message"><%= (update != null) ? update : "" %></span></caption>
                <tr><td>ID: </td><td><input type="text" name="ID" value="<%= customer.getid()%>" readonly="true" /></td></tr>
                <tr><td>Name: </td><td><input type="text" name="name"  placeholder="<%= (nameError != null) ? nameError :customer.getName() %>" /></td></tr>
                <tr><td>Gender: </td><td><input name="gender" placeholder="<%= (genderError != null) ? genderError :customer.getGender() %>"/></td></tr>
                <tr><td>DOB: </td><td><input type="date" name="dob" value="<%= customer.getDob()%>"/></td></tr>
                <tr><td>Mobile Number: </td><td><input type="text" name="phone" placeholder="<%= (phoneError != null) ? phoneError :customer.getPhone() %>"/></td></tr>
                <tr><td>Email: </td><td><input type="text" name="email" value="<%= customer.getEmail()%>" readonly="true"/></td></tr>
                <tr><td>Password: </td><td><input type="password" name="password" placeholder="<%= (passError != null) ? passError :customer.getPassword() %>" /></td></tr>

                <tr><input type="hidden" name="submitted" value="submitted"></tr>
                <tr>
                    <td>
                        <% if (emailView != null) { %>
                        <a class="button" href="adminSearch.jsp">Accounts</a> 
                        <%} else { %>
                        <a class="button" href="main.jsp">Dashboard</a>
                        <%}%>
                    </td>
                    <td>
                        <input class="button" type="submit" value="Update" /> 
                        <a class="button" href="/group2/CustomerdeleteServlet">Delete</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>        

</body>
</html>

