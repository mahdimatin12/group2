package com.model.dao;

//import com.model.Blog;

import com.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class CustomerSqlDAO {

    private Statement st;
    private PreparedStatement updateSt;
    private String updateQuery = "UPDATE moviedb.customers SET NAME=?, PASSWORD=? WHERE ID=?";
    private PreparedStatement deleteCustomerSt;
    private String deleteCustomerQuery = "DELETE FROM moviedb.customers WHERE ID=?";  
    //private PreparedStatement deleteBlogSt;
    //private String deleteBlogQuery = "DELETE FROM moviedb.blogs WHERE ID=?";
    //private BlogSqlDAO blogSqlDAO;
    
    public CustomerSqlDAO(Connection connection) throws SQLException {
        //this.blogSqlDAO = new BlogSqlDAO(connection);
        this.st = connection.createStatement();
        this.updateSt = connection.prepareStatement(updateQuery);
        this.deleteCustomerSt = connection.prepareStatement(deleteCustomerQuery);  
        //this.deleteBlogSt = connection.prepareStatement(deleteBlogQuery);
    }

    //Create Query
    public void create(String name, String email, String password) throws SQLException {
        String columns = "INSERT INTO moviedb.customers(NAME,EMAIL,PASSWORD)";
        String values = "VALUES('" + name + "','" + email + "','" + password + "')";
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
                String email = rs.getString(3);
                String password = rs.getString(4);               
                return new Customer(ID, name, email, password);
            }
        }
        return null;
    }
    
        //Read Query - Read One
    public Customer getCustomer(String email) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE EMAIL='"+ email+"'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(3);

            if (email.equals(currentEmail)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);
                
                String password = rs.getString(4);                
                return new Customer(ID, name, email, password);
            }
        }
        return null;
    }
    
     //Read Query - Read One by Email and Password
    public Customer login(String email, String password) throws SQLException {
        String query = "SELECT * FROM moviedb.customers WHERE EMAIL='"+ email+"' AND PASSWORD='"+password+"'";
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String currentEmail = rs.getString(3);
            String currentPassword = rs.getString(4);

            if (email.equals(currentEmail)&&password.equals(currentPassword)) {
                int ID = Integer.parseInt(rs.getString(1));
                String name = rs.getString(2);            
               
                return new Customer(ID, name, email, password);
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
            String email = rs.getString(3);
            String password = rs.getString(4);
           
            //List<Blog> blogs = blogSqlDAO.getBlogs(ID);
            Customer customer = new Customer(ID, name, email, password);
            //customer.addAll(blogs);
           temp.add(customer);
        }    
        return temp;
    }
    
    //Update Query (Name, Password) by ID
    public void update(String name, String password, int ID) throws SQLException{
        updateSt.setString(1, name);
        updateSt.setString(2, password);        
        updateSt.setString(3, Integer.toString(ID));
        int row = updateSt.executeUpdate();
        System.out.println("Row "+row+" has been successflly updated");
    }
   
    //Delete Query - by ID
    public void delete(int ID) throws SQLException{
        //blogSqlDAO.archive(ID);
        //deleteBlogSt.setString(1, ""+ID);
        //int y = deleteBlogSt.executeUpdate();
        deleteCustomerSt.setString(1, ""+ID);
        int x = deleteCustomerSt.executeUpdate();
        
        System.out.println("Customer has been successflly deleted");
    }
}
