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
public class Payment {
    
    // Private variables
    int id;
    String date, amount, type, school;
    
    // Empty constructor
    public Payment() {
        
    }
    
    // Constructor
    public Payment(int id, String date, String amount, String type, String school) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.school = school;
    }
    
    // Constructor
    public Payment(String date, String amount, String type, String school) {
        this.date = date;
        this.amount = amount;
        this.type = type;
        this.school = school;
    }
    
    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }    
    
}
