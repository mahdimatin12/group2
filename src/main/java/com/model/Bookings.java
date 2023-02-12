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


/*
Bookings implements the Serializable interface,
so objects of this class can be serialized,
so they can be saved or sent.

The class uses JAXB annotations to specify how the objects of this class should be marshalled and unmarshalled from and to XML.

*/
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "bookings")
public class Bookings implements Serializable {
    @XmlElement(name = "booking")
    
    //The class has a private field "bookings" which is a list of "Booking" objects.
    private List<Booking> bookings = new ArrayList<>();
    
    
    //The class provides methods to add, remove, and retrieve "Booking" objects from the list.
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
