package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
*@author Zaki|236370.
*is is the class for the Customers that represents a list of "Customer" objects.
*The Customers class implements the Serializable interface
*@XmlAccessorType(XmlAccessType.FIELD)" and "@XmlRootElement(name = "Customers")" annotations, which are part of the Java Architecture for XML Binding (JAXB) framework.
* The class also has methods to add or remove an "Customer" object from the list
* methods to retrieve an "Customer" object from the list based on its email, password, or ID. 
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "customers")

public class Customers implements Serializable {

    @XmlElement(name = "customer")
    //field
    private List<Customer> customers = new ArrayList<>();

    public Customers() {
    }
    //methods to add Customer

    public void addAll(List<Customer> temp) {
        this.customers.addAll(temp);
    }

    public void add(Customer customer) {
        this.customers.add(customer);
    }

    // method to retrieve an "Customer" object from the list based on its email, password 
    public Customer customer(String email, String password) {
        return this.customers.stream().filter(customer -> customer.login(email, password)).findAny().orElse(null);
    }
    // methods to retrieve an "Customer" object from the list based on its email

    public Customer customer(String email) {
        return this.customers.stream().filter(customer -> customer.match(email)).findAny().orElse(null);
    }
    //methods to retrieve an "Customer" object from the list based on its ID

    public Customer customer(int ID) {
        return this.customers.stream().filter(customer -> customer.match(ID)).findAny().orElse(null);
    }
    //Customers can be accessed using the "getCustomers" method

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    //methods to remove Customer

    public void remove(Customer other) {
        customers.removeIf(u -> u.match(other));
    }
    //methods to show Customer

    public void show() {
        this.customers.forEach(u -> System.out.println(u));
    }
}
