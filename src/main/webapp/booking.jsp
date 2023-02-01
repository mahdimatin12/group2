<%@page import="com.model.Customer"%>
<%@page import="com.model.dao.CustomerSqlDAO"%>
<%@page import="com.model.Booking"%>
<%@page import="java.util.List"%>
<%@page import="com.model.dao.BookingSqlDAO"%>
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
                    <li><a href="#">ABOUT THE APP</a></li>
                    <li><a href="main.jsp">Dashboard</a></li>                    
                </ul>
            </nav>

            <span>&#9654</span>
            <h1>mymovies<span style="font-size: 0.5em;margin-left: 0;">.com</span></h1>
        </header>
        <article class="main">

            <%
                Customer customer = (Customer) session.getAttribute("customer");
                int id = customer.getid();
                BookingSqlDAO bookingSqlDAO = (BookingSqlDAO) session.getAttribute("bookingSqlDAO");
                List<Booking> bookings = bookingSqlDAO.getBookings(id);
                int rows = bookings.size();
            %>

            <div class="tableDiv"> 
                <button id="add"><img src="image/blue-plus-11976.svg" height="10px" width="10px"> Add</button>
                <br>
                <br>
                <from  class="orderForm" id="oForm" action="/group2/BookingServlet">
                    <label for="date">Pick a date</label>
                    <input type="date" name="date">
                    <input id="selectMovie" type="submit" value="Select Movie">
                </from>
                <br>
                <br>
                <form id="mForm" class="mForm">
                    <label for="pet-select">Choose a movie:</label>

                    <select name="movies" id="movie-select">
                        <option value="">--Please choose a movie--</option>
                        <% List<String> movies = bookingSqlDAO.getMovies();
                            for (String movie : movies) {%> 

                        <option value="<%= movie%>"><%=movie%></option>
                        <% }%>
                    </select>

                </form>
                <script>
                    const obtn = document.getElementById('add');
                    function toggleForm() {
                        const of = document.getElementById('oForm');
                        of.classList.toggle('orderForm');
                    }
                    obtn.addEventListener('click', toggleForm, false);
                </script>
                <script>
                    const selectMovie = document.getElementById('selectMovie');
                    function togglemForm() {
                        const mf = document.getElementById('mForm');
                        mf.classList.toggle('mForm');
                    }
                    selectMovie.addEventListener('click', togglemForm, false);
                </script>

                <table class="bookings" width="100%">

                    <caption>
                        You have <strong style="color:#e52323"><%= rows%></strong> bookings
                    </caption>
                    <colgroup>
                        <col id="poster">
                        <col id="name">
                        <col id="date">
                    </colgroup>
                    <tr>
                        <th scope="col">Poster</th>
                        <th scope="col">Movie Name</th>
                        <th scope="col">Booking Date</th>
                    </tr>
                    <% for (Booking booking : bookings) {%>
                    <tr>
                        <td class="poster"><img src="<%= booking.getImgUrl()%>" width="50" height="50" alt="star"></td>
                        <td> <%= booking.getName()%> </td>
                        <td><%= booking.getDate()%></td>                    
                    </tr>
                    <% }%>
                </table>
            </div>
        </article>
        <footer>
            <p>SIUA 2023, UST, Sydney.
            <p>Step It Up Australia, group two. Assessment 3, the Movie web-app built using Java.</p>
            <p>Contact: <a href="mailto:nobody@nowhere.com">group3@ust.com</a></p>
        </footer>
    </body>
</html>