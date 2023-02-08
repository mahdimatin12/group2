<%-- 
    Document   : logged
    Created on : 27-Nov-2022, 9:08:38 PM
    Author     : 236370
--%>

<%@page import="com.model.Admin"%>
<%@page import="com.model.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <script type="text/javascript" src="js/index.js"></script>
        <title>Message page</title>
        <style>

            body{
                background-color:#fafafa;
            }


            form {
                max-width: 300px;
                margin: 10px auto;
                padding: 10px 20px;
                background-color:#dfd3c3;
                border-radius: 8px;
            }



            #deletebtn{
                background: #ee6f57;  
                margin-left: 15px;
                padding-left: 25px;
                padding-right: 20px;
            }
            #cancelbtn{
                background:#b7b7b7;  
                margin-left: 15px;
                padding-left: 25px;
                padding-right: 25px;
            }

            .button{
                font-family: "Roboto", sans-serif;
                font-size: 18px;
                font-weight: bold;
                background:  #5783db;
                width: 140px;
                padding: 10px;
                text-align: center;
                text-decoration: none;
                border: none;
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
            img{

                height: 50px;
                width: 50px; 
                margin-top: 5%;
                margin-left:40%;
            }

        </style>
    </head>
    <body>

        <%
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer != null) {
        %>  
        <form action="/group2/DeleteCustomerServlet" >
            <div id="myModal" class="modal fade">
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <img src="image/icon2.png">
                            <h4 style="text-align: center; font-size: 30px; margin-top:5%;">Are you Sure?</h4>
                        </div>
                        <div class="modal-body">
                            <p style="text-align: center; font-size: 15px; margin-bottom: 7%" >Do you really want to delete your account?This process cannot be undone.</p>
                        </div>
                        <div class="modal-footer">
                            <button class="button" id="deletebtn">Delete</button>
                            <a class="button" id="cancelbtn" href="usersaccount.jsp">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <% } else {
            Admin admin = (Admin) session.getAttribute("admin");%>
            
           <form action="/group2/DeleteAdminServlet">
            <div id="myModal" class="modal fade">
                <div class="modal-dialog modal-confirm">
                    <div class="modal-content">
                        <div class="modal-header">
                            <img src="image/icon2.png">
                            <h4 style="text-align: center; font-size: 30px; margin-top:5%;">Are you Sure?</h4>
                        </div>
                        <div class="modal-body">
                            <p style="text-align: center; font-size: 15px; margin-bottom: 7%" >Do you really want to delete your account?This process cannot be undone.</p>
                        </div>
                        <div class="modal-footer">
                            <button class="button" id="deletebtn">Delete</button>
                            <a class="button" id="cancelbtn" href="usersaccount.jsp">Cancel</a>
                        </div>
                    </div>
                </div>
            </div>
        </form> 

        <%}%>
    </body>
</html>
