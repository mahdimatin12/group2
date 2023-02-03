

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Now</title>
        <link rel="stylesheet" href="css/styles.css">
    </head>
    <body>
        <header>
            <div class="Navbar">             

                <nav>
                    <ul class="nav">
                        <li><a class="button" href="index.jsp"> Home </a></li>
                        <li><a class="button" href="login.jsp"> Login </a></li>

                    </ul>
                </nav>
            </div>
        </header>
        <main>
            <article>
                <div class="loginContent">           

                    <%
                        String nameError = (String) session.getAttribute("nameError");
                        session.removeAttribute("nameError");
                        String phoneError = (String) session.getAttribute("phoneError");
                        session.removeAttribute("phoneError");
                        String emailError = (String) session.getAttribute("emailError");
                        session.removeAttribute("emailError");
                        String passError = (String) session.getAttribute("passError");
                        session.removeAttribute("passError");
                        String error = (String) session.getAttribute("error");
                        session.removeAttribute("error");
                    %>
                    <h1>Register Form <span style="font-size: 10px; color: orange;"><%= (error != null) ? error : ""%></span></h1>

                    <button id="p1"><img src="image/person-244.svg" style="height: 20px;width: 20px;">Customer</button>

                    <form id="form1" method="post" action="/group2/CreateCustomerServlet" >                      

                        <input name="name" type="text" placeholder="<%= (nameError != null) ? nameError : "Customer name"%>">

                        <select name="gender" id="gender">     
                            <option value="">--Please choose gender--</option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>

                        <input name="dob" type="date">

                        <input type="text" name="phone" placeholder="<%= (phoneError != null) ? phoneError : "Phone number"%>">

                        <input name="email" type="text" placeholder="<%= (emailError != null) ? emailError : "Customer email"%>">

                        <input name="password" type="password" placeholder="<%= (passError != null) ? passError : "Customer password"%>">
                        <input style="cursor: pointer" type="submit" value="REGISTER">

                    </form>
                </div>
            </article>
        </main>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>

    </body>
</html>
