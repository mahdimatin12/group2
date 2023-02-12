package com.model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/*
 *This is the class for the Admin.
 *@author Zaki|236370
 *The class implements the Serializable interface
 *The class has several fields:
 * There are several constructors provided to create instances of the Admin class.
 *There are also getters and setters provided for each field of the class
 *toString method that returns a string representation of the Admin instance.
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "admin")
public class Admin implements Serializable {
    //The fields of the class.

    private int id;
    private String name;
    private String gender;
    private String dob;
    private String phone;
    private String email;
    private String password;

    public Admin() {
    }

    //constructors provided to intialize the feild of the Admin 
    public Admin(int id, String name, String gender, String dob, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    //constructors provided to intialize the feild of the Admin 

    public Admin(String name, String gender, String dob, String phone, String email, String password) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    //updates all the fields of the Admin instance with the provided arguments
    public void update(int id, String name, String gender, String dob, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    // returns true if the provided email and password match the email and password of the Admin instance
    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    //returns true if the Admin feild has the same id as pararmeter
    public boolean match(int id) {
        return this.id == id;
    }
     //returns true if the Admin feild has the same email as pararmeter
    public boolean match(String email) {
        return this.email.equals(email);
    }
   //returns true if the Admin  has the same admin as pararmeter
    public boolean match(Admin other) {
        return this.id == other.id;
    }
    //getters and setters provided for each field of the class
    
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
   //toString method that returns a string representation of the Admin feilds.
    @Override
    public String toString() {
        return "Admin{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", phone=" + phone + ", email=" + email + ", password=" + password + '}';
    }

}
