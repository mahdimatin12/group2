package com.model.dao;

import com.model.Booking;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class BookingSqlDAO {
    
    private Statement st;
    
    
    public BookingSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        
    }

    //Create Booking for a User by ID
    public void create(int customerid, String date) throws SQLException {
        String columns = "INSERT INTO moviedb2.bookings(customerid,date)";
        String values = "VALUES('" + customerid + "','" + date + "')";
        st.executeUpdate(columns + values);
    }

    //Read All Bookings for a User
    public List<Booking> getBookings() throws SQLException {
        String fetch = "select imgUrl,name , date\n"
                + "from moviedb2.movies m\n"
                + "inner join moviedb2.movies_bookings mb\n"
                + "on m.ID = mb.moviesid\n"
                + "inner join moviedb2.bookings b\n"
                + "on b.id = mb.bookingid\n";
        
        ResultSet rs = st.executeQuery(fetch);
        
        List<Booking> temp = new ArrayList<>();
        
        while (rs.next()) {
            String imgUrl = rs.getString(1);
            String name = rs.getString(2);
            String date = rs.getString(3);
            temp.add(new Booking(imgUrl, name, date));
        }
        return temp;
    }
    
    //Read All movies
    public List<String> getMovies() throws SQLException {
        String fetch = "select name from moviedb2.movies;";
        
        ResultSet rs = st.executeQuery(fetch);
        
        List<String> temp = new ArrayList<>();
        
        while (rs.next()) {            
            String name = rs.getString(1);           
            temp.add(name);
        }
        return temp;
    }
    
   
    //Read a Booking by user ID
    public List<Booking> getBookings(int ID) throws SQLException {
        String fetch = "select imgUrl,name , date\n"
                + "from moviedb2.movies m\n"
                + "inner join moviedb2.movies_bookings mb\n"
                + "on m.ID = mb.moviesid\n"
                + "inner join moviedb2.bookings b\n"
                + "on b.id = mb.bookingid\n"
                + "WHERE customerid="+ID;
        ResultSet rs = st.executeQuery(fetch);
        
        List<Booking> temp = new ArrayList<>();
        
        while (rs.next()) {
            String imgUrl = rs.getString(1);
            String name = rs.getString(2);
            String date = rs.getString(3);
            temp.add(new Booking(imgUrl, name, date));
        }
        return temp;
        
    }

    
    //archive blog
//    public void archive(int ID) throws SQLException {
//        List<Blog> blogs = getBlogs(ID);
//
//        for (Blog blog : blogs) {
//            create(ID, blog.getText(), "mydb.blogshistory");
//        }
//    }
}
