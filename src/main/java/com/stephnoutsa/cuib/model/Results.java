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
public class Results {
    
    // Private variables
    int id;
    String year, semester, matricule, url;
    
    // Empty constructor
    public Results() {
        
    }
    
    // Constructor
    public Results(int id, String year, String semester, String matricule, String url) {
        this.id = id;
        this.year = year;
        this.semester = semester;
        this.matricule = matricule;
        this.url = url;
    }
    
    // Constructor
    public Results(String year, String semester, String matricule, String url) {
        this.year = year;
        this.semester = semester;
        this.matricule = matricule;
        this.url = url;
    }
    
    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }    
    
}
