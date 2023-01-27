package com.model;

import java.io.Serializable;
import java.util.Random;
import javax.management.relation.Role;

public class Admin implements Serializable {

    private int id;
    private String name;
    private String email;
    private String password;
    

    public Admin() {
    }

    public Admin(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Admin(String name, String email, String password) {
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

    public boolean login(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
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
        return id + "\t" + name + "\t" + email + "\t";
    }
}
