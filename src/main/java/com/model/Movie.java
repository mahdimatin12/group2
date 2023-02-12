package com.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
// This annotation indicates that the fields of the class should be used for the XML representation
@XmlAccessorType(XmlAccessType.FIELD)
// This annotation indicates that the class should be treated as the root element of the XML representation
@XmlRootElement(name = "movie")
public class Movie implements Serializable {

    private int id;
    private String name;
    private String genre;
    private int year;
    private String description;
    private String runtime;
    private String imgUrl;
    private String vidUrl;

    // Default constructor
    public Movie() {
    }
    // Constructor with all fields as arguments

    public Movie(int id, String name, String genre, int year, String description, String runtime, String imgUrl, String vidUrl) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.runtime = runtime;
        this.imgUrl = imgUrl;
        this.vidUrl = vidUrl;
    }

    // Constructor without id
    public Movie(String name, String genre, int year, String description,
            String runtime, String imgUrl, String vidUrl) {
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.runtime = runtime;
        this.imgUrl = imgUrl;
        this.vidUrl = vidUrl;
    }
    // Method to update all fields of the movie    

    public void update(int id, String name, String genre, int year, String description, String runtime, String imgUrl, String vidUrl) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.year = year;
        this.description = description;
        this.runtime = runtime;
        this.imgUrl = imgUrl;
        this.vidUrl = vidUrl;
    }
    // Method to check if the movie has a specific id

    public boolean match(int id) {
        return this.id == id;
    }
// Method to check if the movie has a specific name (ignoring case)

    public boolean match(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    // Method to check if the movie is equal to another movie (based on id)
    public boolean match(Movie other) {
        return this.id == other.id;
    }
    // Getters and setters for all fields

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
