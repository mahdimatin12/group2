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

@Path("sqlapi")
public class CustomersSqlService {

    @GET
    @Path("customers") //http://localhost:8080/group2/rest/sqlapi/customers
    @Produces(MediaType.APPLICATION_XML)
    public Customers getCustomers() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        Customers customers = new Customers();
        customers.addAll(customerSqlDAO.getCustomers());
        return customers;
    }
    
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
    @GET
    @Path("savecustomer/{name}-{gender}-{dob}-{phone}-{email}-{password}")//http://localhost:8080/group2/rest/sqlapi/savecustomer/Sergio-Female-2005-234567-sergio.s@movie.com-sergio123
    @Produces(MediaType.APPLICATION_XML)
    public Response saveCustomer(@PathParam("name") String name,@PathParam("gender") String gender, @PathParam("dob") String dob, 
                                 @PathParam("phone") String phone, @PathParam("email") String email,
                                 @PathParam("password") String password) throws JAXBException, FileNotFoundException, ClassNotFoundException, 
                                 SQLException, InstantiationException, IllegalAccessException, IOException {
     
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
       // List<Customer> customers = customerSqlDAO.getCustomers();
        Customer customer = new Customer(name, gender,dob, phone, email, password);
        Customers customers = new Customers();
        customers.addAll(customerSqlDAO.getCustomers());
        customers.add(customer);
        customerSqlDAO.create(name, gender, dob, phone, email, password);
        customers.addAll(customerSqlDAO.getCustomers());
        return Response.status(200).entity(customer).build();
    }
}
