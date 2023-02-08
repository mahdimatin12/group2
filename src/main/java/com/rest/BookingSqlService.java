package com.rest;

import com.model.Booking;
import com.model.Bookings;
import com.model.dao.SqlDBConnector;
import com.model.dao.BookingSqlDAO;
import com.model.dao.SqlDB;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@Path("sqlapi")
public class BookingSqlService {

    @GET
    @Path("bookings")
    @Produces(MediaType.APPLICATION_XML)
    public Bookings getBookings() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
        Bookings bookings = new Bookings();
        bookings.addAll(bookingSqlDAO.getBookings());
        return bookings;
    }

    // add a record to the movies_bookings table:
    @GET
    @Path("addmb/{moviesid}-{customerid}")
    @Consumes(MediaType.APPLICATION_XML)
    public String addmb(@PathParam("moviesid") int moviesid, @PathParam("bookingid") int bookingid) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
        bookingSqlDAO.addMovieBooking(moviesid, bookingid);
        return "<success> Success!</success>";
    }

    // add to the bookings table:
//    @GET
//    @Path("addb/{date}-{customerid}")
//    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
//    public Response addb(@PathParam("date") String date, @PathParam("customerid") int customerid) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
//        Booking booking = new Booking(date, customerid);
//        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
//        Bookings bookings = (Bookings) bookingSqlDAO.getBookings();
//        bookings.addAll((List<Booking>) bookings);
//
//        return Response.status(200).entity(bookings).build();
//    }

    // add a booking:
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

    // add a booking to the movies_bookings table:
//    @GET
//    @Path(value = "booking/ID/{ID}")
//    @Produces(value = MediaType.APPLICATION_XML)
//    public void getBooking(@Suspended final AsyncResponse asyncResponse, @PathParam(value = "ID") final int ID) {
//        executorService.submit(new Runnable() {
//            public void run() {
//                try {
//                    asyncResponse.resume(doGetBooking(ID));
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SQLException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InstantiationException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }
//
//    private Booking doGetBooking(@PathParam("ID") int ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
//        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
//        BookingSqlDAO blogSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
//
//        Booking booking = bookingSqlDAO.getBooking(ID);
//        return booking;
//    }
//
//    @GET
//    @Path(value = "booking")
//    @Produces(value = MediaType.APPLICATION_XML)
//    public void fetchBooking(@Suspended final AsyncResponse asyncResponse, @QueryParam(value = "ID") final int ID) {
//        executorService.submit(new Runnable() {
//            public void run() {
//                try {
//                    asyncResponse.resume(doFetchBooking(ID));
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SQLException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InstantiationException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(BookingSqlService.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }
//
//    private Bookings doFetchBooking(@QueryParam("ID") int ID) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
//        BookingSqlDAO bookingSqlDAO = new BookingSqlDAO(new SqlDBConnector().connection());
//
//        Booking booking = bookingSqlDAO.getBooking(ID);
//        Bookings bookings = new Bookings();
//        bookings.add(booking);
//        return bookings;
//////    }
}
