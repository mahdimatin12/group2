package com.rest;

import com.model.Customer;
import com.model.Customers;
import com.model.dao.CustomerSqlDAO;
import com.model.dao.SqlDBConnector;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

//Java class that provides REST API services for a customer database. 
@Path("sqlapi")
public class CustomersSqlService {
    
    //@GET, which are used to handle GET requests.
    //The method retrieves all customers from the database and returns the result as an XML format.
    @GET
    @Path("customers") //http://localhost:8080/group2/rest/sqlapi/customers
    @Produces(MediaType.APPLICATION_XML)
    public Customers getCustomers() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        Customers customers = new Customers();
        customers.addAll(customerSqlDAO.getCustomers());
        return customers;
    }
    
    //@GET, which are used to handle GET requests.
    //This method retrieves a single customer based on the provided customer ID. 
    @GET
    @Path("customer/ID/{ID}") //http://localhost:8080/group2/rest/sqlapi/customer/ID/
    @Produces(MediaType.APPLICATION_XML)
    public Customers getCustomer(@PathParam("ID") int ID) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        
        Customer customer = customerSqlDAO.getCustomer(ID);
        Customers customers = new Customers();
        customers.add(customer);
        return customers;
    }
    
    //@GET, which are used to handle GET requests.
    //This method saves a new customer to the database. 
    @GET
    @Path("savecustomer/{name}-{gender}-{dob}-{phone}-{email}-{password}")//http://localhost:8080/group2/rest/sqlapi/savecustomer/Fanna-Female-20050525-0234567231-fanna.f@movie.com-Fanna123
    @Produces(MediaType.APPLICATION_XML)
    public Response saveCustomer(@PathParam("name") String name,@PathParam("gender") String gender, @PathParam("dob") String dob, 
                                 @PathParam("phone") String phone, @PathParam("email") String email,
                                 @PathParam("password") String password) throws JAXBException, FileNotFoundException, ClassNotFoundException, 
                                 SQLException, InstantiationException, IllegalAccessException, IOException {
     
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        Customer customer = new Customer(name, gender,dob, phone, email, password);
        customerSqlDAO.create(name, gender, dob, phone, email, password);
        Customer cust = new Customer();
        
        Customers customers = new Customers();
        cust = customerSqlDAO.getCustomer(email);
        customers.add(cust);
        return Response.status(200).entity(customers).build();
    }
}
