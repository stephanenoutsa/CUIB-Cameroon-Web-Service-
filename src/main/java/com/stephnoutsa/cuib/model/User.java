/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.model;

/**
 *
 * @author stephnoutsa
 */
public class User {
    
    // Private variables
    int id;
    String email, phone, dob, gender, password, role;
    
    // Empty constructor
    public User() {
        
    }
    
    // Constructor
    public User(int id, String email, String phone, String dob, String gender, String password, String role) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }
    
    // Constructor
    public User(String email, String phone, String dob, String gender, String password, String role) {
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.password = password;
        this.role = role;
    }
    
    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }    
        
}
