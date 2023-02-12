/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rest;

/**
 *
 * @author 236365
 */
import com.model.Movie;
import com.model.Movies;
import com.model.dao.MovieSqlDAO;
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

// Define the base URI path for the resource
@Path("movieapi")
public class MovieSqlService {
    // This method retrieves all the movies from the database
    // and returns the result in XML format
    @GET
    @Path("movies") // URL for accessing this method://http://localhost:8080/group2/rest/movieapi/movies
    @Produces(MediaType.APPLICATION_XML)
    public Movies getMovies() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        // Connect to the database and retrieve movie data using MovieSqlDAO
        MovieSqlDAO movieSqlDAO = new MovieSqlDAO(new SqlDBConnector().connection());
        Movies movies = new Movies();
        movies.addAll(movieSqlDAO.getMovies());
        return movies;
    }
    
    // This method retrieves a specific movie from the database
    // based on the movie ID and returns the result in XML format
    @GET
    @Path("movie/ID/{ID}") // URL for accessing this method://http://localhost:8080/group2/rest/movieapi/movie/ID/1000007
    @Produces(MediaType.APPLICATION_XML)
    public Movies getMovie(@PathParam("ID") int ID) throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        MovieSqlDAO movieSqlDAO = new MovieSqlDAO(new SqlDBConnector().connection());
        Movie movie = movieSqlDAO.getMovie(ID);
        Movies movies = new Movies();
        movies.add(movie);
        return movies;
    }
    
    // This method saves a new movie to the database and returns the result in XML format
    @GET
    @Path("savemovie/{name}-{genre}-{year}-{description}-{runtime}-{imgurl}-{vidurl}")
    @Produces(MediaType.APPLICATION_XML)
    public Response saveMovie(@PathParam("name") String name, @PathParam("genre") String genre,
            @PathParam("year") int year, @PathParam("description") String description,
            @PathParam("runtime") String runtime, @PathParam("imgurl") String imgurl,
            @PathParam("vidurl") String vidurl)
            throws JAXBException, FileNotFoundException, ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException, IOException {

        MovieSqlDAO movieSqlDAO = new MovieSqlDAO(new SqlDBConnector().connection());
        movieSqlDAO.create(name, genre, year, description, runtime, imgurl, vidurl);
        Movie movie = movieSqlDAO.getMovie(name);
        Movies movies=new Movies();        
        movies.add(movie);      
         return Response.status(200).entity(movies).build();     
        
    }
    
   
}
