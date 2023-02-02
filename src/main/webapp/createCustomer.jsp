

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Now</title>
        <link rel="stylesheet" href="css/style.css">
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
                <div class="content">           
                    <%
                        String exist = (String) session.getAttribute("error");
                    %>            
                    
                        <fieldset>
                            <legend>
                                <%= (exist != null) ? exist : "Register Form"%>
                            </legend>
                            
                            <button id="p1"><img src="image/person-244.svg" style="height: 20px;width: 20px;">Customer</button>
                            <form method="POST" id="registerForm" action="/group2/CreateCustomerServlet">                      

                            <input name="name" type="text" placeholder="Customer name">

                            <select name="gender" id="gender">
                                <option value="">--Please choose gender--</option>
                                <option value="male">Male</option>
                                <option value="female">Female</option>
                            </select>


                            <input name="dob" type="date">

                            <input type="text" name="phone" placeholder="Phone number">

                            <input name="email" type="text" placeholder="Customer email">

                            <input name="password" type="password" placeholder="Customer password">
                            <input style="cursor: pointer" type="submit" value="REGISTER">

                        </fieldset>             
                    </form>
                </div>
            </article>
        </main>
        

    </body>
</html>
