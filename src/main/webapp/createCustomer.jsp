

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
            <nav class="clear">
                <ul>
                    <li><a class="button" href="index.jsp"> HOME </a></li>
                    <li><a class="button" href="customer.jsp">CANCEL</a></li>
                    <li><a class="button" href="main.jsp">DASHBOARD</a></li>
                </ul>
            </nav>
            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
    
            <article class="main">
                <div class="loginContent">           

                    <%
                        String nameError = (String) session.getAttribute("nameError");
                        session.removeAttribute("nameError");
                        String phoneError = (String) session.getAttribute("phoneError");
                        session.removeAttribute("phoneError");
                        String emailError = (String) session.getAttribute("emailError");
                        session.removeAttribute("emailError");
                        String dobError = (String) session.getAttribute("dobError");
                        session.removeAttribute("dobError");
                        String passError = (String) session.getAttribute("passError");
                        session.removeAttribute("passError");
                        String genderError = (String) session.getAttribute("genderError");
                        session.removeAttribute("genderError");
                        String exist = (String) session.getAttribute("Error");
                        session.removeAttribute("Error");                      
                        
                    %>
                                       
                    <h1 style="width: bold; color:black;">Register Form <span style="font-size: 10px; color: orange;" class="message"><%= (exist != null) ? exist : ""%><%= (dobError != null) ? dobError : ""%></span></h1>   
                                      
                    <button id="p1"><img src="image/person-244.svg" style="height: 20px;width: 20px;">Customer</button>

                    <form id="form1" method="post" action="/group2/CreateCustomerServlet" >                      

                        <input name="name" type="text" placeholder="<%= (nameError != null) ? nameError : "Full Name"%>">

                        <select name="gender" id="gender" style="width: 80%; margin-left:6px;">     
                            <option value=""><%= (genderError != null) ? genderError : "Gender"%></option>
                            <option value="male">Male</option>
                            <option value="female">Female</option>
                        </select>

                        <input name="dob" type="date" placeholder="<%= (dobError != null) ? dobError : "date of birth"%>">

                        <input type="text" name="phone" placeholder="<%= (phoneError != null) ? phoneError : "Phone number"%>">

                        <input name="email" type="text" placeholder="<%= (emailError != null) ? emailError : "Email"%>">

                        <input name="password" type="password" placeholder="<%= (passError != null) ? passError : "Password"%>">
                        <input style="cursor: pointer" type="submit" value="REGISTER">

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

