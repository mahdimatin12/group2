package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// This annotation indicates that the fields of the class should be used for the XML representation
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "movies")
public class Movies implements Serializable {
   
    @XmlElement(name = "movie")
    private List<Movie> movies = new ArrayList<>();

    public Movies() {
    }
    //Add list of movies to movies
    public void addAll(List<Movie> temp){
        this.movies.addAll(temp);
    }
    // Add one movie to movies
    public void add(Movie movie){
        this.movies.add(movie); 
    }
    //Return a movie by name
    public Movie movie(String name) {
        return this.movies.stream().filter(movie -> movie.match(name)).findAny().orElse(null);
    }
    //Return a movie by ID
    public Movie movie(int ID) {
        return this.movies.stream().filter(movie -> movie.match(ID)).findAny().orElse(null);
    }

    public void show() {
        this.movies.forEach(movie -> System.out.print(movie));
    }
    //Remove a movie 
    public void remove(Movie other) {
        movies.removeIf(u -> u.match(other));
    }
    //Remove a movie by name
    public void removeByName(String name) {
        movies.removeIf(u -> u.match(name));
    }

}
