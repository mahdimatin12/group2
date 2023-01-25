package com.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private int id;
    private String name;
    private String genre;
    private String year;    
    private String description;
    private String runtime;
    private String imgUrl;
    private String vidUrl;
   

    public Movie() {
    }

    public Movie(int id, String name, String genre, String year, String description, String runtime, String imgUrl, String vidUrl) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.runtime = runtime;
        this.imgUrl = imgUrl;
        this.vidUrl = vidUrl;
    }

  

    public Movie(String name, String genre, String year, String description, String runtime, String imgUrl, String vidUrl) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.runtime = runtime;
        this.imgUrl = imgUrl;
        this.vidUrl = vidUrl;
    }    

    public boolean match(int id) {
        return this.id == id;
    }

    public boolean match(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public boolean match(Movie other) {
        return this.id == other.id;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }

    @Override
    public String toString() {
        return "Movie{" + "id=" + id + ", name=" + name + ", genre=" + genre + ", year=" + year + ", description=" + description + ", runtime=" + runtime + ", imgUrl=" + imgUrl + ", vidUrl=" + vidUrl + '}';
    }

    
    

}
