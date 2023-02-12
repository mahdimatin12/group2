package com.rest;

import com.model.Booking;
import com.model.Bookings;
import com.model.dao.SqlDBConnector;
import com.model.dao.BookingSqlDAO;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;


/*
BookingSqlService is a RESTful web service that interacts with a moviedb SQL database.
The service provides 4 endpoints to retrieve and add data to the database.



 */
@Path("bookingapi")
public class BookingSqlService {
    
    /*
    getBookingsws: This method retrieves all bookings from the database.
    It uses the BookingSqlDAO class to interact with the database and retrieve the data.
    The method is accessible through the URL "http://localhost:8080/group2/rest/bookingapi/bookings". 
    It returns the result as an XML document.    
    */
    @GET
    @Path("bookings")
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBookingsws() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
        Bookings bookings = new Bookings();
        bookings.addAll(bookingSqlDAO.getBookingsws());
        return bookings;
    }
    
    
    
    /*
    This method retrieves a specific booking from the database based on the ID.
    It uses the BookingSqlDAO class to interact with the database and retrieve the data.
    The method is accessible through the URL "http://localhost:8080/group2/rest/bookingapi/booking/ID/{ID}" where {ID} is the ID of the booking you want to retrieve.
    It returns the result as an XML document.
    */
    @GET
    @Path("booking/ID/{ID}") 
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBooking(@PathParam("ID") int ID) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());

        Booking booking = bookingSqlDAO.getBooking(ID);
        Bookings bookings = new Bookings();
        bookings.add(booking);
        return bookings;
    }
    
    /*
    This method adds a record to the "movies_bookings" table in the database.
    It uses the BookingSqlDAO class to interact with the database and add the data.
    The method is accessible through the URL "http://localhost:8080/group2/rest/bookingapi/addmb/{moviesid}-{customerid}" where {moviesid} is the ID of the movie and {customerid} is the ID of the customer.
    The method returns a simple string indicating success or failure.    
    */
    @GET
    @Path("addmb/{moviesid}-{customerid}")
    @Consumes(MediaType.APPLICATION_XML)
    public String addmb(@PathParam("moviesid") int moviesid, @PathParam("bookingid") int bookingid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
        bookingSqlDAO.addMovieBooking(moviesid, bookingid);
        return "<success> Success!</success>";
    }

    /*
    This method adds a new booking to the database.
    It uses the BookingSqlDAO class to interact with the database and add the data.
    The method is accessible through the URL "http://localhost:8080/group2/rest/bookingapi/addbooking/{date}-{customerid}-{moviesid}" where {date} is the date of the booking,
    {customerid} is the ID of the customer,
    and {moviesid} is the ID of the movie.
    The method returns the added booking as an XML document.
    */
    @GET
    @Path("addbooking/{date}-{customerid}-{moviesid}")
    @Produces(MediaType.APPLICATION_XML)
    public Bookings addBooking(@PathParam("date") String date, @PathParam("customerid") int customerid, @PathParam("moviesid") int moviesid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {

        // create a booking and get back its ID: (insert into bookings table)
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
        int bookingid = 0;
        bookingSqlDAO.create(customerid, date);

        bookingid = bookingSqlDAO.currentBooking();
        Booking booking = null;
        Bookings bookings = new Bookings();
        //Booking booking = bookings.booking(bookingid);
//        String msg = null;
        if (bookingid > 0) {
//            //Now, insert into movies_bookings table: using moviesid & bookingid:
            bookingSqlDAO.addMovieBooking(moviesid, bookingid);

            booking = bookingSqlDAO.getBooking(bookingid);
            bookings.add(booking);
        }
        //return Response.status(200).entity(bookings).build();
        return bookings;
    }

}
