package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movies implements Serializable {

    private List<Movie> movies = new ArrayList<>();

    public Movies() {
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
