package com.rest;

import com.model.Customer;
import com.model.Customers;
import com.model.dao.SqlDBConnector;
import com.model.dao.CustomerSqlDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

/**
 * @author 236370|Zaki RESTful Web Service that implements several endpoints to
 * perform CRUD operations (Create, Read, Update, Delete) on a database service
 * is using JAXB to handle the marshalling and unmarshalling of XML data.
 *
 */
@Path("customerapi")
public class CustomerSqlService {

    /**
     * retrieves a list of all customers from the database and returns it in XML
     * format.
     *
     */
    @GET
    @Path("customers") //http://localhost:8080/group2/rest/customerapi/customers
    @Produces(MediaType.APPLICATION_XML)
    public Customers getCustomers() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        Customers customers = new Customers();
        customers.addAll(customerSqlDAO.getCustomers());
        return customers;
    }

    /**
     * retrieves a specific customer from the database based on their ID and
     * returns it in XML format.
     *
     */
    @GET
    @Path("customer/ID/{ID}") //http://localhost:8080/group2/rest/customerapi/customer/ID/10042
    @Produces(MediaType.APPLICATION_XML)
    public Customers getCustomer(@PathParam("ID") int ID) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());
        Customer customer = customerSqlDAO.getCustomer(ID);
        Customers customers = new Customers();
        customers.add(customer);
        return customers;
    }

    /**
     * creates a new customer and stores it in the database. The customer's
     * information is passed as path parameters in the URL.
     *
     */
    @GET //http://localhost:8080/group2/rest/customerapi/savecustomer/AliSina-Male-19990505-0756237564-ali.sina65@movie.com-Sinajan123
    @Path("savecustomer/{name}-{gender}-{dob}-{phone}-{email}-{password}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response saveCustomer(@PathParam("name") String name,
            @PathParam("gender") String gender,
            @PathParam("dob") String dob,
            @PathParam("phone") String phone,
            @PathParam("email") String email,
            @PathParam("password") String password) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

        CustomerSqlDAO customerSqlDAO = new CustomerSqlDAO(new SqlDBConnector().connection());

        Customer customer = new Customer(name, gender, dob, phone, email, password);
        customerSqlDAO.create(name, gender, dob, phone, email, password);
        Customers customers = new Customers();
        customers.add(customer);
        return Response.status(200).entity(customer).build();
    }

}
