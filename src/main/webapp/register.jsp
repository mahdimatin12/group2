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
                    String error = (String) session.getAttribute("error");
                    String nameerror = (String) session.getAttribute("nameerror");
                    String emailerror = (String) session.getAttribute("emailerror");
                    String passerror = (String) session.getAttribute("passerror");
                    String phoneerror = (String) session.getAttribute("phoneerror");
                %>
                <h1>Register As <span style="font-size: 10px; color: orange;"><%= (error != null) ? error : ""%></span></h1>


                <button id="p1"><img src="image/admin-9575.svg" style="height: 20px;width: 20px;">Admin</button>
                <button id="p2"><img src="image/person-244.svg" style="height: 20px;width: 20px;">Customer</button>

                <!-- -----------------------------------------------Admin Register Form  ---------------------------------------------------------- -->


                <form id="form1" class="admin" action="/group2/AdminRegisterServlet" method="POST">

                    <input name="name"  type="text" placeholder="<%= (nameerror != null) ? nameerror : "Enter your name (Admin)"%>">

                    <select name="gender" id="gender">
                        <option value="">--Please choose gender--</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>

                    <input name="dob"  type="date">
                    <input type="text" name="phone" placeholder="<%= (phoneerror != null) ? phoneerror : "Enter your phone (Admin)"%>">
                    <input name="email" type="text" placeholder="<%= (emailerror != null) ? emailerror : "Enter your email (Admin)"%>">
                    <input name="password" type="password" placeholder="<%= (passerror != null) ? passerror : "Enter your password (Admin)"%>">
                    <input type="hidden" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">   

                </form>

                <!-- -----------------------------------------------Customer Register Form---------------------------------------------------------- -->
                <%

                    String nameerror2 = (String) session.getAttribute("nameerror2");
                    String emailerror2 = (String) session.getAttribute("emailerror2");
                    String passerror2 = (String) session.getAttribute("passerror2");
                    String phoneerror2 = (String) session.getAttribute("phoneerror2");
                %>

                <form id="form2" class="admin" action="/group2/CustomerRegisterServlet" method="POST">

                    <input name="name"  type="text" placeholder="<%= (nameerror2 != null) ? nameerror2 : "Enter your name (Customer)"%>">                  

                    <select name="gender" id="gender">
                        <option value="">--Please choose gender--</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>


                    <input name="dob"  type="date">
                    <input type="text" name="phone" placeholder="<%=(phoneerror2 != null) ? phoneerror2 : "Enter your phone (Customer)"%>">
                    <input name="email" type="text" placeholder="<%= (emailerror2 != null) ? emailerror2 : "Enter your email (Customer)"%>">
                    <input name="password" type="password" placeholder="<%= (passerror2 != null) ? passerror2 : "Enter your password (Customer)"%>">
                    <input type="hidden" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">   

                </form>
            </div>
            <!-- -----------------------------------------------JavaScript for Differentiate ---------------------------------------------------------- -->
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
        <!-- -----------------------------------------------Footer ---------------------------------------------------------- -->
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </div>

</body>
</html>
