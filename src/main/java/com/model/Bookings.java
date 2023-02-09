package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 236347
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookings")
public class Bookings implements Serializable {
    @XmlElement(name = "booking")
    private List<Booking> bookings = new ArrayList<>();

    public Bookings() {
    }
    
    public void addAll(List<Booking> temp){
        this.bookings.addAll(temp);
    }
    public void add(Booking booking){
        this.bookings.add(booking);
    }
    
        
    public Booking booking(String name){
        return this.bookings.stream().filter(booking -> booking.match(name)).findAny().orElse(null);
    }
    
    public Booking booking(int ID){
        return this.bookings.stream().filter(booking -> booking.match(ID)).findAny().orElse(null);
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }  
     public void remove(Booking other){
        bookings.removeIf(u -> u.match(other));
    }
    
    public void show(){
        this.bookings.forEach(u -> System.out.println(u));
    }
            
}
