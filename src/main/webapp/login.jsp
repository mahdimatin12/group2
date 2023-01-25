<%-- 
    Document   : login
    Created on : 24 Jan. 2023, 8:33:55 pm
    Author     : 236347
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>

            const a = document.getElementById('admin');
            const b = document.getElementById('customer');
            a.style.display = 'none';
            b.style.display = 'none';
            
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>



        <form id="admin" action="/AdminServlet" method="POST">
            <input name="email" type="text">
            <input name="password" type="password">         
            <input type="submit" value="Submit">            
        </form>

        <form id="customer" action="/CustomerServlet" method="POST">
            <input name="email" type="text">
            <input name="password" type="password">            
            <input type="submit" value="Submit">            
        </form>

    </body>
</html>
