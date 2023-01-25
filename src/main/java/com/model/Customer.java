package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer implements Serializable{
    private int id;
    private String name;
    private String email;
    private String password;
    private List<Booking> bookings = new ArrayList();

    public Customer() {
    }

    public Customer(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;        
    }

    public Customer(String name, String email, String password) {
        //this.id = (new Random()).nextInt(999999);
        this.name = name;
        this.email = email;
        this.password = password;
        
    }
    
    public void update(int id, String name, String email, String password){
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;        
    }

    public void addAll(List<Booking> temp){
        bookings.addAll(temp);
    }
    
    public boolean login(String email, String password){
        return this.email.equals(email)&&this.password.equals(password);
    }    
    
    public boolean match(int id){
        return this.id == id;
    }
    
    public boolean match(String email){
        return this.email.equals(email);
    }
    
    public boolean match(Customer other){
        return this.id == other.id;
    }
    
    public void add(String date, int movieid,int customerid){
        this.bookings.add(new Booking(bookings.size()+1, date, movieid, customerid));
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    @Override
    public String toString() {
        return  id + "\t" + name + "\t" + email + "\t\t";
    }    
    
}
