package com.rest;

import com.model.dao.CustomerSqlDAO;
import static com.sun.jersey.core.header.FormDataContentDisposition.name;
import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;



@Path("customerapi")
public class CustomerService {
    
// @Context
//    private ServletContext application;
//    private CustomerSqlDAO getCustomerSqlDAO() {
//        synchronized (application) {
//            CustomerSqlDAO customerSqlDAO = (CustomerSqlDAO) application.getAttribute("customerSqlDAO");
//            if (customerSqlDAO == null) {
//                
//                customerSqlDAO.create(name, gender, dob, phone, email, password);
//                application.setAttribute("customerSqlDAO", customerSqlDAO);
//            }
//            return userDAO;
//        }
//    }

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome to Math Service";
    }

}
