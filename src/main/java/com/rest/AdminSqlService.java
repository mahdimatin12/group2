/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

import com.model.Admin;
import com.model.Admins;
import com.model.dao.AdminSqlDAO;
import com.model.dao.SqlDBConnector;
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
 *@author 236370|Zaki
 * RESTful Web Service that implements several endpoints to perform CRUD operations (Create, Read, Update, Delete) on a database 
 * service is using JAXB to handle the marshalling and unmarshalling of XML data.
 * 
 */
@Path("adminapi")
public class AdminSqlService {

    /**
     *  retrieves a list of all admin from the database and returns it in XML format.
     *
     */
    @GET
    @Path("admins") //http://localhost:8080/group2/rest/adminapi/admins
    @Produces(MediaType.APPLICATION_XML)
    public Admins getAdmins() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        AdminSqlDAO adminSqlDAO = new AdminSqlDAO(new SqlDBConnector().connection());
        Admins admins = new Admins();
        admins.addAll(adminSqlDAO.getAdmins());
        return admins;
    }

    /**
     * retrieves a specific admin from the database based on their ID and returns it in XML format.
     *
     */
    @GET
    @Path("admin/ID/{ID}") //http://localhost:8080/group2/rest/adminapi/admin/ID/1025
    @Produces(MediaType.APPLICATION_XML)
    public Admins getAdmin(@PathParam("ID") int ID) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        AdminSqlDAO adminSqlDAO = new AdminSqlDAO(new SqlDBConnector().connection());
        Admin admin = adminSqlDAO.getAdmin(ID);
        Admins admins = new Admins();
        admins.add(admin);
        return admins;
    }

    /**
     *creates a new admin and stores it in the database. The admin's information is passed as path parameters in the URL.
     *
     */

    @GET //http://localhost:8080/group2/rest/adminapi/saveadmin/Seema-Female-19990505-0756237564-seema.s65@movie.com-Seemah123
    @Path("saveadmin/{name}-{gender}-{dob}-{phone}-{email}-{password}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response saveAdmin(@PathParam("name") String name,
            @PathParam("gender") String gender,
            @PathParam("dob") String dob,
            @PathParam("phone") String phone,
            @PathParam("email") String email,
            @PathParam("password") String password) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

        AdminSqlDAO adminSqlDAO = new AdminSqlDAO(new SqlDBConnector().connection());

        Admin admin = new Admin(name, gender, dob, phone, email, password);
        Admins admins = new Admins();
        admins.addAll(adminSqlDAO.getAdmins());
        admins.add(admin);
        adminSqlDAO.create(name, gender, dob, phone, email, password);
        admins.addAll(adminSqlDAO.getAdmins());
        return Response.status(200).entity(admin).build();
    }
}
