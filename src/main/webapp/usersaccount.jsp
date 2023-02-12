<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Customer"%>
<%@page import="com.model.Admin"%>
<%@page import="com.model.dao.AdminSqlDAO"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Account Page</title>
        <link href="css/ZStyles.css" rel="stylesheet">
        <style>
           .trcustomer{
                width: 100%;
               background-color: yellow;
            }

        </style>
    </head>

    <body >
        <!-------------- Header Of the page contain Logout to main home ---------------------->
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="aboutus.jsp">ABOUT THE APP</a></li>
                    <li><a href="/group2/LogoutServlet" >LOGOUT</a></li>
                    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header> 


        <!------------------------ Admin Details before and after update data  ------------------------>

        <%

            Admin admin = (Admin) session.getAttribute("admin");
            if (admin != null) {

                String nameerror = (String) session.getAttribute("nameerror");
                session.removeAttribute("nameerror");
                String passerror = (String) session.getAttribute("passerror");
                session.removeAttribute("passerror");
                String phoneerror = (String) session.getAttribute("phoneerror");
                session.removeAttribute("phoneerror");
                String doberror = (String) session.getAttribute("doberror");
                session.removeAttribute("doberror");
                String gendererror = (String) session.getAttribute("gendererror");
                session.removeAttribute("gendererror");

        %>
        <%AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
            String updatemsg = (String) session.getAttribute("updatemsg");

        %>
        <article class ="main">

            <form method="POST" action="/group2/AdminAccountServlet">
                <table class="account-table" >
                    <tr><th style="background-color:#552e5a;">Admin Account</th><th style="color:greenyellow;background-color:#552e5a"><%= (updatemsg != null) ? updatemsg : ""%> </th></tr>
                    <tr><td>ID:</td> <td><input type="text" name="ID" value="<%= admin.getid()%>" readonly="true" /></td> </tr>
                    <tr><td>Name:</td> <td><input type="text" name="name" value="<%=admin.getName()%>"/><p class="datafont"><%= (nameerror != null) ? nameerror : ""%></p></td> </tr>  
                    <tr><td>Gender:</td> <td><input type="text" name="gender" value="<%=admin.getGender()%>"/><p class="datafont"><%= (gendererror != null) ? gendererror : ""%></p></td></tr> 
                    <tr><td>DOB:</td> <td><input type="date" name="dob" value="<%=admin.getDob()%>" /><p class="datafont"><%= (doberror != null) ? doberror : ""%></p></td> </tr> 
                    <tr><td>Mobile:</td> <td><input type="text" name="phone" value="<%=admin.getPhone()%>" /><p class="datafont"><%= (phoneerror != null) ? phoneerror : ""%></p></td></tr> 
                    <tr><td>Email:</td> <td><input type="text" name="email" value="<%= admin.getEmail()%>" readonly="true"/></td> </tr> 
                    <tr><td>Password:</td> <td><input type="password" name="password" value="<%= admin.getPassword()%>" /><p class="datafont"><%= (passerror != null) ? passerror : ""%></p></td> </tr> 
                    <tr><input type="hidden" name="submitted" value="submitted"></tr>
                    <tr>
                        <td>
                            <a class="button" href="main.jsp">Dashboard</a>
                        </td>
                        <td>
                            <input class="button" type="submit" value="Update" /> 
                            <a class="button" href="deletemsg.jsp">Delete</a>
                        </td>
                    </tr>
                </table>   
            </form>
        </article>

        <!------------------- Customer Details before and after update data  ------------------->

        <% } else {
            Customer customer = (Customer) session.getAttribute("customer");

            String nameerror = (String) session.getAttribute("nameerror");
            session.removeAttribute("nameerror");
            String passerror = (String) session.getAttribute("passerror");
            session.removeAttribute("passerror");
            String phoneerror = (String) session.getAttribute("phoneerror");
            session.removeAttribute("phoneerror");
            String doberror = (String) session.getAttribute("doberror");
            session.removeAttribute("doberror");
            String gendererror = (String) session.getAttribute("gendererror");
            session.removeAttribute("gendererror");

        %>
        <% CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) session.getAttribute("customerSqlDAO");
            String updatemsg1 = (String) session.getAttribute("updatemsg");
            session.removeAttribute("updatemsg");
        %>
        <article class ="main">

            <form method="POST" action="/group2/CustomerAccountServlet">
                <table  class="account-table"  >
                    <tr><th style="background-color:#552e5a;">Customer Account</th><th style="color:greenyellow ;background-color:#552e5a;"><%= (updatemsg1 != null) ? updatemsg1 : ""%> </th></tr>
                    <tr><td>ID:</td> <td><input type="text" name="ID" value="<%= customer.getid()%>" readonly="true" /></td> </tr>
                    <tr><td>Name:</td> <td><input type="text" name="name" value="<%=customer.getName()%>"/><p class="datafont"><%= (nameerror != null) ? nameerror : ""%></p></td> </tr>  
                    <tr><td>Gender:</td> <td><input type="text" name="gender" value="<%=customer.getGender()%>"/><p class="datafont"><%= (gendererror != null) ? gendererror : ""%></p></td></tr> 
                    <tr><td>DOB:</td> <td><input type="date" name="dob" value="<%=customer.getDob()%>" /><p class="datafont"><%= (doberror != null) ? doberror : ""%></p></td> </tr> 
                    <tr><td>Mobile:</td> <td><input type="text" name="phone" value="<%=customer.getPhone()%>" /><p class="datafont"><%= (phoneerror != null) ? phoneerror : ""%></p></td></tr> 
                    <tr><td>Email:</td> <td><input type="text" name="email" value="<%= customer.getEmail()%>" readonly="true"/></td> </tr> 
                    <tr><td>Password:</td> <td><input type="password" name="password" value="<%= customer.getPassword()%>" /><p class="datafont"><%= (passerror != null) ? passerror : ""%></p></td> </tr> 
                    <tr><input type="hidden" name="submitted" value="submitted"></tr>
                    <tr>
                        <td>
                            <a class="button" href="main.jsp">Dashboard</a>
                        </td>
                        <td>
                            <input class="button" id="updatebtn" type="submit" value="Update" /> 

                            <a class="button" id="deletebtn" href="deletemsg.jsp">Delete</a>
                        </td>
                    </tr>
                </table>   
            </form>
        </article> 

        <%  }%>    

        <!---------------- Footer of the Page ----------------->

        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </body>
</html>