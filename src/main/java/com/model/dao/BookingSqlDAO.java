package com.model.dao;

import com.model.Booking;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
BookingSqlSAO(Data Access Object) is for a movie booking system.
The DAO uses a SQL database to store information about bookings & movies.
The code contains several methods to perform CRUD (Create, Read, Update, and Delete) operations on the bookings data.

*/

public class BookingSqlDAO {

    private BookingSqlDAO bookingSqlDAO;
    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "update moviedb.bookings b\n"
            + "join moviedb.movies_bookings mb\n"
            + "on b.ID = mb.bookingid\n"
            + "join moviedb.movies m\n"
            + "on mb.moviesid = m.ID\n"
            + "set b.date =?,mb.moviesid =?\n"
            + "where mb.ID =?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM moviedb.movies_bookings WHERE ID=?";

    public BookingSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
    }

    //Create Booking for a User by ID
    /*
    This method creates a new booking in the database.    
    It takes two parameters a customer ID and a String Date.       
    */
    public void create(int customerid, String date) throws SQLException {
        String columns = "INSERT INTO moviedb.bookings(customerid,date)";
        String values = "VALUES('" + customerid + "','" + date + "')";
        st.executeUpdate(columns + values);
    }
   

    // return booking current/last booking-id from booking table:
    //This method returns the last booking ID in the bookings table. So that I can add multiple movies to this booking ID.
    public int currentBooking() throws SQLException {
        String fetch = "select ID from moviedb.bookings order by ID desc limit 1";
        ResultSet rs = st.executeQuery(fetch);

        int currentBookingID = 0;
        while (rs.next()) {
            currentBookingID = Integer.parseInt(rs.getString(1));
        }

        return currentBookingID;
    }

   
    //Read All Bookings for a User
    //this method returns a list of bookings for a particular user, based on the customer ID.
    public List<Booking> getBookings(int customerid) throws SQLException {
        String fetch = "SELECT name,imgUrl,bookingid,date\n"
                + "FROM moviedb.movies m\n"
                + "JOIN moviedb.movies_bookings mb ON m.ID = mb.moviesid\n"
                + "JOIN moviedb.bookings b ON mb.bookingid = b.ID\n"
                + "where customerid =" + "'" + customerid + "'";

        ResultSet rs = st.executeQuery(fetch);

        List<Booking> temp = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString(1);
            String imgUrl = rs.getString(2);
            int bookingid = Integer.parseInt(rs.getString(3));
            String date = rs.getString(4);
            temp.add(new Booking(name, imgUrl, bookingid, date));
        }
        return temp;
    }

    // get booking by id:
    //This method returns a specific booking based on its ID in the movies_bookings table.
    public Booking getBooking(int mbID) throws SQLException {
        String fetch = "SELECT *\n"
                + "FROM moviedb.movies m\n"
                + "JOIN moviedb.movies_bookings mb ON m.ID = mb.moviesid\n"
                + "JOIN moviedb.bookings b ON mb.bookingid = b.ID\n"
                + "where mb.ID =" + "'" + mbID + "'";

        ResultSet rs = st.executeQuery(fetch);
        Booking booking = null;

        while (rs.next()) {
            String name = rs.getString(2);
            String imgUrl = rs.getString(7);
            int ID = Integer.parseInt(rs.getString(9));
            int bookingid = Integer.parseInt(rs.getString(11));
            String date = rs.getString(14);
            booking = new Booking(ID, name, imgUrl, date, bookingid);
        }

        return booking;
    }

    // get all bookings:
    public List<Booking> getBookings() throws SQLException {
        String fetch = "SELECT name,imgUrl,bookingid,date\n"
                + "FROM moviedb.movies m\n"
                + "JOIN moviedb.movies_bookings mb ON m.ID = mb.moviesid\n"
                + "JOIN moviedb.bookings b ON mb.bookingid = b.ID\n";

        ResultSet rs = st.executeQuery(fetch);

        List<Booking> temp = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString(1);
            String imgUrl = rs.getString(2);
            int bookingid = Integer.parseInt(rs.getString(3));
            String date = rs.getString(4);
            temp.add(new Booking(name, imgUrl, bookingid, date));
        }
        return temp;
    }

    //get booking date by movies_bookings table (ID):
    public String getBookingDate(int mbID) throws SQLException {
        String fetch = "select date from moviedb.bookings b\n"
                + "join  moviedb.movies_bookings mb\n"
                + "on b.ID = mb.bookingid where mb.ID =" + "'" + mbID + "'";

        ResultSet rs = st.executeQuery(fetch);
        String bookingDate = null;
        while (rs.next()) {
            bookingDate = rs.getString(1);
        }
        return bookingDate;
    }

    // get all bookings for rest api call:
    public List<Booking> getBookingsws() throws SQLException {
        String fetch = "SELECT *\n"
                + "FROM moviedb.movies m\n"
                + "JOIN moviedb.movies_bookings mb ON m.ID = mb.moviesid\n"
                + "JOIN moviedb.bookings b ON mb.bookingid = b.ID\n";

        ResultSet rs = st.executeQuery(fetch);

        List<Booking> temp = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString(2);
            String imgUrl = rs.getString(7);
            int ID = Integer.parseInt(rs.getString(9));
            int bookingid = Integer.parseInt(rs.getString(11));
            String date = rs.getString(14);

            temp.add(new Booking(ID, name, imgUrl, date, bookingid));
        }
        return temp;
    }

    // Get a movie name by it's ID:
    public String getMovieName(int id) throws SQLException {
        String fetch = "select name from moviedb.movies where ID=" + "'" + id + "'";
        ResultSet rs = st.executeQuery(fetch);
        String movieName = null;
        while (rs.next()) {

            movieName = rs.getString(1);
        }

        return movieName;
    }

    //Read All movies
    public List<String> getMovies() throws SQLException {
        String fetch = "select name from moviedb.movies";
        ResultSet rs = st.executeQuery(fetch);
        List<String> temp = new ArrayList<>();

        while (rs.next()) {
            String name = rs.getString(1);
            temp.add(name);
        }
        return temp;
    }

    // get movie ID by name:
    public int getMovieID(String movieName) throws SQLException {
        String fetch = "select ID from moviedb.movies where name=" + "'" + movieName.trim() + "'";
        ResultSet rs = st.executeQuery(fetch);
        int movieID = 0;

        while (rs.next()) {
            movieID = Integer.parseInt(rs.getString(1));
        }
        return movieID;

    }

    // get mb.ID by moviesid and bookingid
    public int getmbID(int moviesid, int bookingid) throws SQLException {
        String fetch = "select ID from moviedb.movies_bookings where moviesid=" + "'" + moviesid + "'"
                + "and bookingid=" + "'" + bookingid + "'";
        ResultSet rs = st.executeQuery(fetch);
        int mbID = 0;
        while (rs.next()) {
            mbID = Integer.parseInt(rs.getString(1));
        }
        return mbID;
    }

    // get moviesid by movies.ID:
    public int getMoviesid(int movieID) throws SQLException {
        String fetch = "select moviesid\n"
                + "from moviedb.movies_bookings mb\n"
                + "join moviedb.movies m on m.ID = mb.moviesid\n"
                + "where bookingid =" + "'" + movieID + "'";
        ResultSet rs = st.executeQuery(fetch);
        int moviesid = 0;
        while (rs.next()) {
            moviesid = Integer.parseInt(rs.getString(1));
        }
        return moviesid;
    }

    // Add a movie to movies_bookings table:
    public void addMovieBooking(int moviesid, int bookingid) throws SQLException {
        String columns = "INSERT INTO moviedb.movies_bookings(moviesid,bookingid)";
        String values = "VALUES('" + moviesid + "','" + bookingid + "')";
        st.executeUpdate(columns + values);
    }

    // Update booking date and movie by moviesid and mbID:
    //This method updates a booking's date and movie in the database.
    public void update(String bookingd, int moviesid, int mbID) throws SQLException {
        updateSt.setString(1, bookingd);
        updateSt.setString(2, Integer.toString(moviesid));
        updateSt.setString(3, Integer.toString(mbID));
        int row = updateSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly updated");
    }

    //Delete a booking from movies_bookings- by ID
    //This method deletes a booking from the database.
    public void delete(int mbID) throws SQLException {
        deleteSt.setString(1, "" + mbID);
        int row = deleteSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly deleted");
    }

}
