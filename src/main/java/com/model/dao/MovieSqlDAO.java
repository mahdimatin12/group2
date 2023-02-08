/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.Movie;
import com.model.Booking;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 236365
 */
public class MovieSqlDAO {

    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "UPDATE moviedb.movies SET NAME=?,GENRE=?,YEAR=?,DESCRIPTION=?,RUNTIME=?,IMGURL=?,VIDURL=? WHERE ID=?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM moviedb.movies WHERE ID=?";
    private PreparedStatement deletebookingSt;
    private String deleteQuerybooking = "DELETE FROM moviedb.movies_bookings WHERE moviesid=?";

    public MovieSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
        this.deletebookingSt = connection.prepareStatement(deleteQuerybooking);
    }

    //Add a Movie 
    public void create(String name, String genre, int year, String description, String runtime, String imgurl, String vidurl) throws SQLException {
        String columns = "INSERT INTO moviedb.movies(NAME,GENRE,YEAR,DESCRIPTION,RUNTIME,IMGURL,VIDURL)";
        String values = "VALUES('" + name + "','" + genre + "','" + year + "','" + description + "','" + runtime + "','" + imgurl + "','" + vidurl + "')";
        st.executeUpdate(columns + values);
    }

    //Read Query - Read One Movie 
    public Movie getMovie(int ID) throws SQLException {
        System.out.println(ID);
        String query = "SELECT * FROM moviedb.movies WHERE ID=" + ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int currentID = Integer.parseInt(rs.getString(1));

            if (ID == currentID) {
                String name = rs.getString(2);
                String genre = rs.getString(3);
                int year = Integer.parseInt(rs.getString(4));
                String description = rs.getString(5);
                String runtime = rs.getString(6);
                String imgurl = rs.getString(7);
                String vidurl = rs.getString(8);
                return new Movie(ID, name, genre, year, description, runtime, imgurl, vidurl);
            }
        }
        return null;
    }

    //Read Query - Read One Movie by name
    public Movie getMovie(String moviename) throws SQLException {
        String query = "SELECT * FROM moviedb.movies WHERE name='" + moviename + "'";
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String currentMoviename = rs.getString(2);
            if (moviename.equals(currentMoviename)) {
                int ID = Integer.parseInt(rs.getString(1));
                String genre = rs.getString(3);
                int year =Integer.parseInt(rs.getString(4));
                String description = rs.getString(5);
                String runtime = rs.getString(6);
                String imgurl = rs.getString(7);
                String vidurl = rs.getString(8);
                return new Movie(ID, moviename, genre, year, description, runtime, imgurl, vidurl);
            }
        }
        return null;
    }

    //Read Query - Read All Movies
    public List<Movie> getMovies() throws SQLException {
        String query = "SELECT * FROM moviedb.movies";
        ResultSet rs = st.executeQuery(query);
        List<Movie> temp = new ArrayList<>();
        while (rs.next()) {
            int ID = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String genre = rs.getString(3);
            int year = Integer.parseInt(rs.getString(4));
            String description = rs.getString(5);
            String runtime = rs.getString(6);
            String imgurl = rs.getString(7);
            String vidurl = rs.getString(8);
            temp.add(new Movie(ID, name, genre, year, description, runtime, imgurl, vidurl));
        }
        return temp;
    }

    //Update a Movie by ID
    public void update(String name, String genre, int year, String description, String runtime, String imgurl, String vidurl, int ID) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setString(2, genre);
        updateSt.setString(3, Integer.toString(year));
        updateSt.setString(4, description);
        updateSt.setString(5, runtime);
        updateSt.setString(6, imgurl);
        updateSt.setString(7, vidurl);
        updateSt.setString(8, Integer.toString(ID));
        int row = updateSt.executeUpdate();
        System.out.println("Row " + row + " has been successfully updated");
    }

    //Delete Query - by ID
    public void delete(int ID) throws SQLException {
        
        deleteSt.setString(1, "" + ID);
        int row = deleteSt.executeUpdate();
        System.out.println("Row " + row + " has been successfully deleted");
    }
    
    public void deletebooking(int ID) throws SQLException {
        //String query="select * from moviedb.movies_bookings where moviesid='+ID+'";
        deletebookingSt.setString(1, "" + ID);
        int row = deletebookingSt.executeUpdate();
        System.out.println("Row " + row + " has been successfully deleted");
    }

}
