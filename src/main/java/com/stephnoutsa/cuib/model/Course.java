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
public class Course {  
    
    // Private variables
    int id;
    String code, name, description;
    String[] schools, departments, levels;
    
    // Empty constructor
    public Course() {
        
    }
    
    // Constructor
    public Course(int id, String code, String name, String description, String[] schools, String[] departments, String[] levels) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.description = description;
        this.schools = schools;
        this.departments = departments;
        this.levels = levels;
    }
    
    // Constructor
    public Course(String code, String name, String description, String[] schools, String[] departments, String[] levels) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.schools = schools;
        this.departments = departments;
        this.levels = levels;
    }
    
    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getSchools() {
        return schools;
    }

    public void setSchools(String[] schools) {
        this.schools = schools;
    }

    public String[] getDepartments() {
        return departments;
    }

    public void setDepartments(String[] departments) {
        this.departments = departments;
    }

    public String[] getLevels() {
        return levels;
    }

    public void setLevels(String[] levels) {
        this.levels = levels;
    }    
    
}
