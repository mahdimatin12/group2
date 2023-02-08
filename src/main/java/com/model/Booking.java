/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "booking")
public class Booking implements Serializable {
    private int ID;
    private String movieName;
    private String imgUrl;
    private String date;
    private int bookingid;
   

    public Booking() {
    }

    public Booking(int ID, String movieName, String imgUrl, String date, int bookingid) {
        this.ID = ID;
        this.movieName = movieName;
        this.imgUrl = imgUrl;
        this.date = date;
        this.bookingid = bookingid;
    }
  
    
   
    public Booking(String movieName, String imgUrl, int bookingid, String date) {
        
        this.movieName = movieName;
        this.imgUrl = imgUrl;
        this.bookingid = bookingid;
        this.date = date;
    }

    public int getBookingid() {
        return this.bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public Booking(int ID, String movieName, String imgUrl, String date) {
        this.ID = ID;
        this.movieName = movieName;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public Booking(String movieName, String imgUrl, String date) {

        this.movieName = movieName;
        this.imgUrl = imgUrl;
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public boolean match(int id) {
        return this.ID == id;
    }

    public boolean match(String name) {
        return this.movieName.equals(name);
    }

    public boolean match(Booking other) {
        return this.ID == other.ID;
    }

    @Override
    public String toString() {
        return "Booking{" + "ID=" + ID + ", movieName=" + movieName + ", imgUrl=" + imgUrl + ", date=" + date + '}';
    }

}
