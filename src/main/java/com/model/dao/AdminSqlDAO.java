package com.model.dao;

import com.model.Admin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
*@Author: Zaki|236370
*This is a Java class that performs CRUD (Create, Read, Update, Delete) operations.
*operation should be on "admins" table in a database. 
*The class uses the SQL language to interact with the database.
*/

public class AdminSqlDAO {

    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "UPDATE moviedb.admins SET NAME=?, GENDER=?, DOB=?, PHONE=?, PASSWORD=? WHERE ID=?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM moviedb.admins WHERE ID=?";

    public AdminSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
    }

    //create method creates a new record in the "admins" table by constructing an SQL INSERT statement and executing it using a Statement object.
    public void create(String name, String gender, String dob, String phone, String email, String password) throws SQLException {
        String columns = "INSERT INTO moviedb.admins(NAME,GENDER,DOB,PHONE,EMAIL,PASSWORD)";
        String values = "VALUES('" + name + "','" + gender + "','" + dob + "','" + phone + "','" + email + "','" + password + "')";
        st.executeUpdate(columns + values);
    }

    //The getAdmin methods retrieve information about a single admin from the "admins" table by ID
    public Admin getAdmin(int ID) throws SQLException {
        String query = "SELECT * FROM moviedb.admins WHERE ID=" + ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int currentID = Integer.parseInt(rs.getString(1));

            if (ID == currentID) {
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String dob = rs.getString(4);
                String phone = rs.getString(5);
                String email = rs.getString(6);
                String password = rs.getString(7);

                return new Admin(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //The getAdmin methods retrieve information about a single admin from the "admins" table by email
    public Admin getAdmin(String email) throws SQLException {
        String query = "SELECT * FROM moviedb.admins WHERE EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(6);

            if (email.equals(currentEmail)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String dob = rs.getString(4);
                String phone = rs.getString(5);
                String password = rs.getString(7);

                return new Admin(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //login method retrieves information about an admin based on the email and password by constructing and executing an SQL SELECT statement.
    public Admin login(String email, String password) throws SQLException {
        String query = "SELECT * FROM moviedb.admins WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(6);
            String currentPassword = rs.getString(7);

            if (email.equals(currentEmail) && password.equals(currentPassword)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String dob =rs.getString(4);
                String phone = rs.getString(5);

                return new Admin(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //"getAdmins" method retrieves information about all admins from the "admins" table
    public List<Admin> getAdmins() throws SQLException {
        String query = "SELECT * FROM moviedb.admins";
        ResultSet rs = st.executeQuery(query);
        List<Admin> temp = new ArrayList<>();

        while (rs.next()) {
            int ID = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String gender = rs.getString(3);
            String dob = rs.getString(4);
            String phone = rs.getString(5);
            String email = rs.getString(6);
            String password = rs.getString(7);

            temp.add(new Admin(ID, name, gender, dob, phone, email, password));
        }
        return temp;
    }

    //update method updates the information about an admin in the "admins" table
    public void update(String name,String gender,String dob,String phone, String password, int ID) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setString(2, gender);
        updateSt.setString(3,dob);
        updateSt.setString(4, phone);
        updateSt.setString(5, password);
        updateSt.setString(6, Integer.toString(ID));
        int row = updateSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly updated");
    }

    //delete method deletes an admin's information from the "admins" table 
    public void delete(int ID) throws SQLException {
        deleteSt.setString(1, "" + ID);
        int row = deleteSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly deleted");
    }
}
