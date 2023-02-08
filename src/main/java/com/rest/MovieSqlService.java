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
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBException;

@Path("movieapi")
public class MovieSqlService {

    @GET
    @Path("movies") //http://localhost:8080/group2/rest/sqlapi/movies
    @Produces(MediaType.APPLICATION_XML)
    public Movies getMovies() throws JAXBException, FileNotFoundException, ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException, IOException {
        MovieSqlDAO movieSqlDAO = new MovieSqlDAO(new SqlDBConnector().connection());
        Movies movies = new Movies();
        movies.addAll(movieSqlDAO.getMovies());
        return movies;
    }

    @GET
    @Path("savemovie/{name}-{genre}-{year}-{description}-{runtime}-{imgurl}-{vidurl}")
    @Consumes(MediaType.APPLICATION_XML)
    public Response saveMovie(@PathParam("name") String name, @PathParam("genre") String genre,
            @PathParam("year") int year, @PathParam("description") String description,
            @PathParam("runtime") String runtime, @PathParam("imgurl") String imgurl,
            @PathParam("vidurl") String vidurl)
            throws JAXBException, FileNotFoundException, ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException, IOException {

        MovieSqlDAO movieSqlDAO = new MovieSqlDAO(new SqlDBConnector().connection());
        Movie movie = new Movie(name, genre, year,description,runtime,imgurl,vidurl);
        Movies movies = new Movies();
        movies.addAll(movieSqlDAO.getMovies());
        movies.add(movie);
        movieSqlDAO.create(name, genre, year, description, runtime, imgurl, vidurl);
        movies.addAll(movieSqlDAO.getMovies());
        return Response.status(200).entity(movies).build();     
        
    }
    
   
}
