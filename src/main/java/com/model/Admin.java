package com.model;

import java.io.Serializable;
import java.util.Random;
import javax.management.relation.Role;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "admin")
public class Admin implements Serializable {

    private int id;
    private String name;
    private String gender;
    private String dob;
    private String phone;
    private String email;
    private String password;

    public Admin() {
    }

    public Admin(int id, String name, String gender, String dob, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public Admin(String name, String gender, String dob, String phone, String email, String password) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public void update(int id, String name, String gender, String dob, String phone, String email, String password) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

     public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

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

   

    public boolean match(int id) {
        return this.id == id;
    }

    public boolean match(String email) {
        return this.email.equals(email);
    }

    public boolean match(Admin other) {
        return this.id == other.id;
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
        return "Admin{" + "id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", phone=" + phone + ", email=" + email + ", password=" + password + '}';
    }

  
}
