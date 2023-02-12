<!DOCTYPE html>
<!--
 *@author 236370|Zaki
 * 
 * 
 -->
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Our Team Section</title>
         <link href="css/styles.css" rel="stylesheet">
        <style>
           
            @import url('https://fonts.googleapis.com/css?family=Allura|Josefin+Sans');

            *{
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }

            body{


                font-family: 'Josefin Sans', sans-serif;



            }

            .wrapper{
                margin-top: 10%;
            }

            .wrapper h1{
                font-family: 'Allura', cursive;
                font-size: 52px;
                margin-bottom: 60px;
                text-align: center;
            }

            .team{
                display: flex;
                justify-content: center;
                width: auto;
                text-align: center;
                flex-wrap: wrap;
            }

            .team .team_member{
                background: #fff;
                margin: 5px;
                margin-bottom: 50px;
                width: 300px;
                padding: 20px;
                line-height: 20px;
                color: #8e8b8b;  
                position: relative;
            }

            .team .team_member h3{
                color: #81c644;
                font-size: 26px;
                margin-top: 50px;
            }

            .team .team_member p.role{
                color: #ccc;
                margin: 12px 0;
                font-size: 12px;
                text-transform: uppercase;
            }

            .team .team_member .team_img{
                position: absolute;
                top: -50px;
                left: 50%;
                transform: translateX(-50%);
                width: 100px;
                height: 100px;
                border-radius: 50%;
                background: #fff;
            }

            .team .team_member .team_img img{
                width: 100px;
                height: 100px;
                padding: 5px;
            } 

        </style>
    </head>
    <body>
        <header>
            <nav class="clear">
                <ul>
                    <li><a href="index.jsp">HOME</a></li>
                    <

                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>

        <div class="wrapper">
            <h1>Movieapp Team</h1>
            <div class="team">
                <div class="team_member">
                    <div class="team_img">
                        <img style="border-radius: 50%;" src="image/zaki.jpg" alt="Team_image">
                    </div>
                    <h3>Zaki Haidari</h3>
                    <p class="role">User Registration|01</p>
                    <p>A new user (Admin or 
                        Customer) can sign up 
                        to the Movie system.
                        also can view 
                        their information after 
                        signing up.
                        User can delete and update their own profile.

                    </p>
                </div>
                <div class="team_member">
                    <div class="team_img">
                        <img style="border-radius: 50%;" src="image/ramya.jpg" alt="Team_image">
                    </div>
                    <h3>Ramya Sathish </h3>
                    <p class="role"> Customer Management|02</p>
                    <p>
                        System admin can 
                        create users.
                        System admin can view 
                        all customers. 
                        Customers data should 
                        be read from the 
                        database.System admin can delete and update a customer profile.
                    </p></div>
                <div class="team_member">
                    <div class="team_img">
                        <img style="border-radius: 50%;" src="image/shylaja.png.jpg" alt="Team_image">
                    </div>
                    <h3>Shylaja Thiagarajan</h3>
                    <p class="role">  Movie Management|03</p>
                    <p>An admin can create a 
                        new Movie.Movies 
                        details should be read 
                        from the database.An admin can select 
                        and update and delete and update any movie.

                    </p></div>
                <div class="team_member">
                    <div class="team_img">
                        <img style="border-radius: 50%;" src="image/mahdi.jpg" alt="Team_image">
                    </div>
                    <h3>Mahdi Ghulami</h3>
                    <p class="role"> Booking Managemen|04</p>
                    <p>Customer can create a 
                        new Booking.
                        Bookings
                        details should be read 
                        from the database.Customer can select 
                        and update and delete any movie.
                    </p>
                </div>
            </div>
        </div>	
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group2@ust.com</a></p>
        </footer>
    </body>
</html>