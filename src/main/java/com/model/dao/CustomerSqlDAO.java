package com.model.dao;

import com.model.Customer;
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
*operation should be on "Customers" table in a database. 
*The class uses the SQL language to interact with the database.
 */

public class CustomerSqlDAO {

    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "UPDATE moviedb.customers SET NAME=?,GENDER=?, DOB=?, PHONE=?, PASSWORD=? WHERE ID=?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM moviedb.customers WHERE ID=?";
    private PreparedStatement deleteStatement;
    private String deletebookings = "DELETE FROM moviedb.bookings WHERE customerid=?";

    public CustomerSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
        this.deleteStatement = connection.prepareStatement(deletebookings);
    }

    //create method creates a new record in the "Customers" table by constructing an SQL INSERT statement and executing it using a Statement object.
    public void create(String name, String gender, String dob, String phone, String email, String password) throws SQLException {
        String columns = "INSERT INTO moviedb.customers(NAME,GENDER,DOB,PHONE,EMAIL,PASSWORD)";
        String values = "VALUES('" + name + "','" + gender + "','" + dob + "','" + phone + "','" + email + "','" + password + "')";
        st.executeUpdate(columns + values);
    }

    //The getCustomer methods retrieve information about a single Customer from the "Customers" table by ID
    public Customer getCustomer(int ID) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE ID=" + ID;
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

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //The getCustomer methods retrieve information about a single Customer from the "Customers" table by ID
    public Customer getCustomer(String email) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE EMAIL='" + email + "'";
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

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }
//login method retrieves information about an Customer based on the email and password by constructing and executing an SQL SELECT statement.

    public Customer login(String email, String password) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE EMAIL='" + email + "' AND PASSWORD='" + password + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(6);
            String currentPassword = rs.getString(7);

            if (email.equals(currentEmail) && password.equals(currentPassword)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String gender = rs.getString(3);
                String dob = rs.getString(4);
                String phone = rs.getString(5);

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //"getCustomers" method retrieves information about all Customers from the "Customers" table
    public List<Customer> getCustomers() throws SQLException {
        String query = "SELECT * FROM moviedb.customers";
        ResultSet rs = st.executeQuery(query);
        List<Customer> temp = new ArrayList<>();

        while (rs.next()) {
            int ID = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String gender = rs.getString(3);
            String dob = rs.getString(4);
            String phone = rs.getString(5);
            String email = rs.getString(6);
            String password = rs.getString(7);

            temp.add(new Customer(ID, name, gender, dob, phone, email, password));
        }
        return temp;
    }

    //update method updates the information about an Customer in the "Customers" table
    public void update(String name, String gender, String dob, String phone, String password, int ID) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setString(2, gender);
        updateSt.setString(3, dob.toString());
        updateSt.setString(4, phone);
        updateSt.setString(5, password);
        updateSt.setString(6, Integer.toString(ID));
        int row = updateSt.executeUpdate();
        System.out.println("Row " + row + " has been successfully updated");
    }

    //delete method deletes an Customer's information from the "Customers" table 
    public void deletebookings(int ID) throws SQLException {
        deleteStatement.setString(1, "" + ID);
        int row = deleteStatement.executeUpdate();
        System.out.println("Row " + row + " has been successfully deleted");
    }

    //Delete Customer - by ID
    public void delete(int ID) throws SQLException {
        deleteSt.setString(1, "" + ID);
        int row = deleteSt.executeUpdate();
        System.out.println("Row " + row + " has been successfully deleted");
    }
    
}

