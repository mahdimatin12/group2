package com.model.dao;

import com.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import java.sql.Date;

public class CustomerSqlDAO {

    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "UPDATE moviedb.customers SET NAME=?, PASSWORD=?, WHERE ID=?";
    private PreparedStatement deleteSt;
    private String deleteQuery = "DELETE FROM moviedb.customers WHERE ID=?";

    public CustomerSqlDAO(Connection connection) throws SQLException {
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteSt = connection.prepareStatement(deleteQuery);
    }

    //Create Query
    public void create(String name, String gender, Date dob, String phone, String email, String password) throws SQLException {
        String columns = "INSERT INTO moviedb.customers(NAME,GENDER,DOB,PHONE,EMAIL,PASSWORD)";
        String values = "VALUES('" + name + "','" + gender + "','" + dob + "','" + phone + "','" + email + "','" + password + "')";
        st.executeUpdate(columns + values);
    }

    //Read Query - Read One
    public Customer getCustomer(int ID) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE ID=" + ID;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int currentID = Integer.parseInt(rs.getString(1));

            if (ID == currentID) {
                String name = rs.getString(2);
                String gender = rs.getString(3);
                Date dob = Date.valueOf(rs.getString(4));
                String phone = rs.getString(5);
                String email = rs.getString(6);
                String password = rs.getString(7);

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //Read Query - Read One
    public Customer getCustomer(String email) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE EMAIL='" + email + "'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(6);

            if (email.equals(currentEmail)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                String gender = rs.getString(3);
                Date dob = Date.valueOf(rs.getString(4));
                String phone = rs.getString(5);
                String password = rs.getString(7);

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //Read Query - Read One by Email and Password
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
                Date dob = Date.valueOf(rs.getString(4));
                String phone = rs.getString(5);

                return new Customer(ID, name, gender, dob, phone, email, password);
            }
        }
        return null;
    }

    //Read Query - Read All
    public List<Customer> getCustomers() throws SQLException {
        String query = "SELECT * FROM moviedb.customers";
        ResultSet rs = st.executeQuery(query);
        List<Customer> temp = new ArrayList<>();

        while (rs.next()) {
            int ID = Integer.parseInt(rs.getString(1));
            String name = rs.getString(2);
            String gender = rs.getString(3);
            Date dob = Date.valueOf(rs.getString(4));
            String phone = rs.getString(5);
            String email = rs.getString(6);
            String password = rs.getString(7);

            temp.add(new Customer(ID, name, gender, dob, phone, email, password));
        }
        return temp;
    }

    //Update Query (Name, Password) by ID
    public void update(String name, String gender, Date dob, String phone, String password, int ID) throws SQLException {
        updateSt.setString(1, name);
        updateSt.setString(2, gender);
        updateSt.setString(3, dob.toString());
        updateSt.setString(2, password);
        updateSt.setString(3, Integer.toString(ID));
        int row = updateSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly updated");
    }

    //Delete Query - by ID
    public void delete(int ID) throws SQLException {
        deleteSt.setString(1, "" + ID);
        int row = deleteSt.executeUpdate();
        System.out.println("Row " + row + " has been successflly deleted");
    }
}
