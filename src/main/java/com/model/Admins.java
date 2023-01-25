package com.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Admins implements Serializable{

    private List<Admin> admins = new ArrayList<>();
    
    public Admins() {}
    
    public Admin admin(String email, String password){
        return this.admins.stream().filter(admin -> admin.login(email, password)).findAny().orElse(null);
    }
    
    public Admin admin(String email){
        return this.admins.stream().filter(admin -> admin.match(email)).findAny().orElse(null);
    }
    
    public Admin admin(int ID){
        return this.admins.stream().filter(admin -> admin.match(ID)).findAny().orElse(null);
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }    
}
