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
public class Message {
    
    // Private variables
    int id;
    String title, body, time;
    
    // Empty constructor
    public Message() {
        
    }
    
    // Constructor
    public Message(int id, String title, String body, String time) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.time = time;
    }
    
    // Constructor
    public Message(String title, String body, String time) {
        this.title = title;
        this.body = body;
        this.time = time;
    }
    
    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }    
    
}
