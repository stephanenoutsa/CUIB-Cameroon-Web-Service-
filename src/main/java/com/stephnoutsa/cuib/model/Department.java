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
public class Department {
    
    // Private variables
    int id;
    String school, department, level, timetable;
    
    // Empty constructor
    public Department() {
        
    }
    
    // Constructor
    public Department(int id, String school, String department, String level, String timetable) {
        this.id = id;
        this.school = school;
        this.department = department;
        this.level = level;
        this.timetable = timetable;
    }
    
    // Constructor
    public Department(String school, String department, String level, String timetable) {
        this.school = school;
        this.department = department;
        this.level = level;
        this.timetable = timetable;
    }
    
    // Getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimetable() {
        return timetable;
    }

    public void setTimetable(String timetable) {
        this.timetable = timetable;
    }    
        
}
