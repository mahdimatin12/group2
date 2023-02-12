<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head lang="en">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <title>Movies: Assessment 3</title>
        <link href="css/ZStyles.css" rel="stylesheet">
        <style>


        </style>
    </head>

    <body>
        <!--  
           * Header of the page
        -->
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="aboutus.jsp">ABOUT THE APP</a></li>                        
                    <li><a href="index.jsp">HOME</a></li>
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies.com</h1>
        </header>


        <!-- main content goes here
             *The Sign up form for admin and customer are in.
             *Using radio button for Differentiate  between admin and customer.
             *Using Post method to handle the .
        
        -->
        <article class="main">
            <div class="loginContent" >
                <%
                    String error = (String) session.getAttribute("error");
                    String nameerror = (String) session.getAttribute("nameerror");
                    session.removeAttribute("nameerror");
                    String emailerror = (String) session.getAttribute("emailerror");
                    session.removeAttribute("emailerror");
                    String passerror = (String) session.getAttribute("passerror");
                    session.removeAttribute("passerror");
                    String phoneerror = (String) session.getAttribute("phoneerror");
                    session.removeAttribute("phoneerror");
                    String doberror = (String) session.getAttribute("doberror");
                    session.removeAttribute("doberror");
                    String gendererror = (String) session.getAttribute("gendererror");
                    session.removeAttribute("gendererror");
                %>
                <h1>Sign Up As <span style="font-size: 10px; color: orange;"><%= (error != null) ? error : ""%></h1></span></h1>


                <button id="p1"><img src="image/admin1.png" style="height: 20px;width: 20px;">Admin</button>
                <button id="p2"><img src="image/customer.png" style="height: 20px;width: 20px;">Customer</button>

                <!-- -----------------------------------------------Admin Register Form  ---------------------------------------------------------- -->


                <form  id="form1" class="admin" action="/group2/AdminRegisterServlet" method="POST">

                    <input name="name"  type="text"  placeholder="Enter your name (Admin)"><p class="datafont"><%= (nameerror != null) ? nameerror : ""%></p>

                    <select name="gender" id="gender">
                        <option class="option1" value="">--Select Your Gender--</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select><p class="datafont"><%= (gendererror != null) ? gendererror : ""%></p>

                    <input name="dob"  type="date" placeholder="Dob"><p class="datafont"><%= (doberror != null) ? doberror : ""%></p>
                    <input type="text" name="phone" placeholder="Enter your phone (Admin)"><p class="datafont"><%= (phoneerror != null) ? phoneerror : ""%></p>
                    <input name="email" type="text" placeholder="Enter your email (Admin)"><p class="datafont"><%= (emailerror != null) ? emailerror : ""%></p>
                    <input name="password" type="password" placeholder="Enter your password (Admin)"><p class="datafont"><%= (passerror != null) ? passerror : ""%></p>
                    <input type="hidden" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">
                    <p style="color:black ; font-size: 10px;margin-top: 20px;margin-left:0%; "> Already have an account? <a href="login.jsp"> click here</a></p>

                </form>

                <!-- -----------------------------------------------Customer Register Form---------------------------------------------------------- -->
                <%
                    String error2 = (String) session.getAttribute("error");
                    String nameerror2 = (String) session.getAttribute("nameerror2");
                    session.removeAttribute("nameerror2");
                    String emailerror2 = (String) session.getAttribute("emailerror2");
                    session.removeAttribute("emailerror2");
                    String passerror2 = (String) session.getAttribute("passerror2");
                    session.removeAttribute("passerror2");
                    String phoneerror2 = (String) session.getAttribute("phoneerror2");
                    session.removeAttribute("phoneerror2");
                    String doberror2 = (String) session.getAttribute("doberror2");
                    session.removeAttribute("doberror2");
                    String gendererror2 = (String) session.getAttribute("gendererror2");
                    session.removeAttribute("gendererror");
                %>

                <form id="form2" class="admin" action="/group2/CustomerRegisterServlet" method="POST">


                    <input name="name"  type="text" placeholder="Enter your name (Customer)"><p class="datafont"><%= (nameerror2 != null) ? nameerror2 : ""%></p>

                    <select name="gender" id="gender">
                        <option value="">--Select Your Gender--</option>
                        <option value="Male">Male</option>
                        <option value="Female">Female</option>
                    </select><p class="datafont"><%= (gendererror2 != null) ? gendererror2 : ""%></p>

                    <input name="dob"  type="date" placeholder="Dob"><p class="datafont"><%= (doberror2 != null) ? doberror2 : ""%></p>
                    <input type="text" name="phone" placeholder="Enter your phone (Customer)"><p class="datafont"><%= (phoneerror2 != null) ? phoneerror2 : ""%></p>
                    <input name="email" type="text" placeholder="Enter your email (Customer)"><p class="datafont"><%= (emailerror2 != null) ? emailerror2 : ""%></p>
                    <input name="password" type="password" placeholder="Enter your password (Customer)"><p class="datafont"><%= (passerror2 != null) ? passerror2 : ""%></p>
                    <input type="hidden" value="admin">
                    <input type="submit" value="REGISTER" style="cursor: pointer">  

                      <p style="color:black ; font-size: 10px;margin-top: 20px;margin-left:0%; "> Already have an account? <a href="login.jsp"> click here</a></p>
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
