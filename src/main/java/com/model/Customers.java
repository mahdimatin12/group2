package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")
public class Customers implements Serializable{
  
    @XmlElement(name = "customer")
    private List<Customer> customers = new ArrayList<>();
    
    public Customers() {
    }
    
    public void addAll(List<Customer> temp){
        this.customers.addAll(temp);
    }
    
    public void add(Customer customer){
        this.customers.add(customer);
    }
    
    public Customer customer(String email, String password){
        return this.customers.stream().filter(customer -> customer.login(email, password)).findAny().orElse(null);
    }
    
    public Customer customer(String email){
        return this.customers.stream().filter(customer -> customer.match(email)).findAny().orElse(null);
    }
    
    public Customer customer(int ID){
        return this.customers.stream().filter(customer -> customer.match(ID)).findAny().orElse(null);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }   
    
    public void remove(Customer other){
        customers.removeIf(u -> u.match(other));
    }
    
    public void show(){
        this.customers.forEach(u -> System.out.println(u));
    }
}
