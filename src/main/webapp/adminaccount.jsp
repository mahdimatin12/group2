<%@page import="com.model.Admin"%>
<%@page import="com.model.dao.AdminSqlDAO"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Account Page</title>
        <link href="css/styles.css" rel="stylesheet">
        <style>

            @import url('https://fonts.googleapis.com/css?family=Montserrat|Open+Sans|Roboto');
            *{
                margin:0;
                padding: 0;
                outline: 0;
            }
            .filter{
                position:fixed;
                left: 0;
                top: 0;
                bottom: 0;
                right: 0;
                z-index: 1;
                background: rgb(233,76,161);
                background: -moz-linear-gradient(90deg, rgba(233,76,161,1) 0%, rgba(199,74,233,1) 100%);
                background: -webkit-linear-gradient(90deg, rgba(233,76,161,1) 0%, rgba(199,74,233,1) 100%);
                background: linear-gradient(90deg, rgba(233,76,161,1) 0%, rgba(199,74,233,1) 100%);
                filter: progid:DXImageTransform.Microsoft.gradient(startColorstr="#e94ca1",endColorstr="#c74ae9",GradientType=1);
                opacity: .1;
            }
            table{
                position:fixed;
                z-index: 2;
                left: 50%;
                top: 50%;
                bottom: 50%;
                transform: translate(-50%,-50%);
                width: 40%; 
                border-collapse: collapse;
                border-spacing: 0;
                box-shadow: 0 2px 15px rgba(64,64,64,.7);
                border-radius: 12px 12px 10px 10px;
                overflow: hidden;

            }
            td , th{
                padding: 7px 7px;
                text-align: center;


            }
            th{
                background-color: #999999;
                color: #FBF6FF;
                padding: 15px 15px;
                font-family: 'Open Sans',Sans-serif;
                font-weight: 200;

                font-weight: bold;

            }
            tr{
                width: 100%;
                background-color: #fafafa;
                font-family: 'Montserrat', sans-serif;
            }
            tr:nth-child(even){
                background-color: #eeeeee;
            }
            input{
                width:90%;
                border:1px solid #aaa;
                border-radius:4px;
                margin:0px 0;
                outline:none;
                padding:15px;
                box-sizing:border-box;
                transition:.3s;
                font-weight: bold;
            }

            input:focus{
                border-color:dodgerBlue;
                box-shadow:0 0 8px 0 dodgerBlue;
            }
            .button{
                font-family: "Roboto", sans-serif;
                font-size: 18px;
                font-weight: bold;
                background: #1E90FF;
                width: 140px;
                padding: 10px;
                text-align: center;
                text-decoration: none;

                color: #fff;
                border-radius: 5px;
                cursor: pointer;
                box-shadow: 0 0 10px rgba(0,0,0,1);
                -webkit-transform-duration:0.3;
                transition-duration: 0.3s;
                -webkit-transition-property: box-shadow, transform;


            }
            .button:hover, .button:focus, .button:active{
                box-shadow: 0 0 20px rgba(0,0,0,0.5);
                -webkit-transform: scale(1.1);
                transform: scale(1.1);

            }
            #updatebtn{
                background: green; 

            }
            #deletebtn{
                background: red;  
                margin-left: 15px;
                padding-left: 25px;
                padding-right: 25px;
            }
        </style>

    </head>

    <body>
       <header>
            <nav class="clear">
                <ul>
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="/group2/LogoutServlet" >LOGOUT</a></li>                   
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>

        <%
            AdminSqlDAO adminSqlDAO = (AdminSqlDAO) session.getAttribute("adminSqlDAO");
            Admin admin = (Admin) session.getAttribute("admin");
            String emailView = request.getParameter("emailView");
            String submitted = request.getParameter("submitted");

        %>
        <article class ="main">
            
        <form method="POST" action="/group2/AdminAccountServlet">
            <div class="filter">
            </div>
            <table >
                <tr><th>Admin Account</th><th style="color:greenyellow"><%= (submitted != null) ? "Update is Successful" : ""%> </th></tr>
                <tr><td>ID:</td> <td><input type="text" name="ID" value="<%= admin.getid()%>" readonly="true" /></td> </tr>
                <tr><td>Name:</td> <td><input type="text" name="name" value="<%= admin.getName()%>" /></td> </tr>   
                <tr><td>Gender:</td> <td><input type="text" name="gender" value="<%= admin.getGender()%>" /></td> </tr> 
                <tr><td>DOB:</td> <td><input type="date" name="dob" value="<%= admin.getDob()%>" /></td> </tr> 
                <tr><td>Mobil:</td> <td><input type="text" name="phone" value="<%= admin.getPhone()%>" /></td> </tr> 
                <tr><td>Email:</td> <td><input type="text" name="email" value="<%= admin.getEmail()%>" /></td> </tr> 
                <tr><td>ID:</td> <td><input type="password" name="password" value="<%= admin.getPassword()%>" /></td> </tr> 
                <tr><input type="hidden" name="submitted" value="submitted"></tr>
                <tr>
                    <td>
                        <% if (emailView != null) { %>
                        <a class="button" href="customerView.jsp">Accounts</a> 
                        <%} else { %>
                        <a class="button" href="main.jsp">Dashboard</a>
                        <%}%>
                    </td>
                    <td>
                        <input class="button" id="updatebtn" type="submit" value="Update" /> 
                        <a class="button" id="deletebtn" href="/group2/DeleteAdminServlet">Delete</a>
                    </td>
                </tr>
            </table>   
        </form>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </body>
</html>