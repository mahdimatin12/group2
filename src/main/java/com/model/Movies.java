package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "movies")
public class Movies implements Serializable {
    
    @XmlElement(name = "movie")
    private List<Movie> movies = new ArrayList<>();

    public Movies() {
    }
    
    public void addAll(List<Movie> temp){
        this.movies.addAll(temp);
    }
    
    public void add(Movie movie){
        this.movies.add(movie); 
    }
    
    public Movie movie(String name) {
        return this.movies.stream().filter(movie -> movie.match(name)).findAny().orElse(null);
    }

    public Movie movie(int ID) {
        return this.movies.stream().filter(movie -> movie.match(ID)).findAny().orElse(null);
    }

    public void show() {
        this.movies.forEach(movie -> System.out.print(movie));
    }

    public void remove(Movie other) {
        movies.removeIf(u -> u.match(other));
    }

    public void removeByName(String name) {
        movies.removeIf(u -> u.match(name));
    }

}
