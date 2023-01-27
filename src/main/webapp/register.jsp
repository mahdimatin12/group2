<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Movies: Assessment 3</title>
        <link href="css/styles.css" rel="stylesheet">
    </head>

    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">About The App</a></li>                        
                    <li><a href="index.jsp">Home</a></li>
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies.com</h1>
        </header>

        <!-- main content goes here -->
        <article class="main">
            <div class="loginContent">
                <%
                    String exist = (String) session.getAttribute("error");
                %>
                <h1>Register As <span style="font-size: 10px; color: orange;"><%= (exist != null) ? exist : ""%></span></h1>


                <button id="p1"><img src="image/admin-9575.svg" style="height: 20px;width: 20px;">Admin</button>
                <button id="p2"><img src="image/person-244.svg" style="height: 20px;width: 20px;">Customer</button>

                <form id="form1" class="admin" action="/group2/AdminRegisterServlet" method="POST">

                    <input name="name"  type="text" placeholder="Admin name">                       
                    <input name="email" type="email" placeholder="Admin email">
                    <input name="password" type="password" placeholder="Admin password">
                    <input type="hidden" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">   

                </form>

                <form id="form2" class="admin" action="/group2/CustomerRegisterServlet" method="POST">

                    <input name="name" type="text" placeholder="Customer name">
                    <input name="email" type="email" placeholder="Customer email">
                    <input name="password" type="password" placeholder="Customer password">
                    <input type="hidden" name="role" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">

                </form>
            </div>

            <script>

                const a = document.getElementById('form1');
                const b = document.getElementById('form2');
                const p1 = document.getElementById('p1');
                const p2 = document.getElementById('p2');

                function third() {
                    a.style.display = 'block';
                    b.style.display = 'none';

                }
                function fourth() {
                    a.style.display = 'none';
                    b.style.display = 'block';
                }
                p1.addEventListener('click', third, false);
                p2.addEventListener('click', fourth, false);
            </script>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>
    </div>

</body>
</html>
