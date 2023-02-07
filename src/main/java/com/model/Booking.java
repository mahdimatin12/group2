
package com.model;

import java.io.Serializable;


public class Booking implements Serializable {

    private int id;
    private String date;  
    private int customerid;  
    private String imgUrl;
    private String name;
    

    public Booking() {
    }

    public Booking(int id, String date, int movieid, int customerid) {
        this.id = id;
        this.date = date;     
        this.customerid = customerid;
    }

    public Booking(String date, int movieid, int customerid) {        
        this.date = date;      
        this.customerid = customerid;
    }
    
    public Booking(String imgUrl, String name, String date) {
        this.imgUrl = imgUrl;
        this.name = name;
        this.date = date;
        
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getUserid() {
        return customerid;
    }

    public void setUserid(int customerid) {
        this.customerid = customerid;
    }

    @Override
    public String toString() {
        return "Booking{" + "id=" + id + ", date=" + date + ", movieid="+ ", customerid=" + customerid + '}';
    }
    
    

}
