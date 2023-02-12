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
*is is the class for the Admins that represents a list of "Admin" objects.
*The Admins class implements the Serializable interface
*@XmlAccessorType(XmlAccessType.FIELD)" and "@XmlRootElement(name = "admins")" annotations, which are part of the Java Architecture for XML Binding (JAXB) framework.
* The class also has methods to add or remove an "Admin" object from the list
* methods to retrieve an "Admin" object from the list based on its email, password, or ID. 
*/

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "admins")

public class Admins implements Serializable{
@XmlElement(name = "admin")
   //field
    private List<Admin> admins = new ArrayList<>();
    
    public Admins() {}
    //methods to add admin
    public void addAll(List<Admin> temp){
        this.admins.addAll(temp);
    }
    public void add(Admin admin){
        this.admins.add(admin);
    }
    // method to retrieve an "Admin" object from the list based on its email, password 
    public Admin admin(String email, String password){
        return this.admins.stream().filter(admin -> admin.login(email, password)).findAny().orElse(null);
    }
    // methods to retrieve an "Admin" object from the list based on its email
    public Admin admin(String email){
        return this.admins.stream().filter(admin -> admin.match(email)).findAny().orElse(null);
    }
    //methods to retrieve an "Admin" object from the list based on its ID
    public Admin admin(int ID){
        return this.admins.stream().filter(admin -> admin.match(ID)).findAny().orElse(null);
    }
    //admins can be accessed using the "getAdmins" method
    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }  
    //methods to remove admin
     public void remove(Admin other){
        admins.removeIf(u -> u.match(other));
    }
    //methods to show admins
    public void show(){
        this.admins.forEach(u -> System.out.println(u));
    }
}
