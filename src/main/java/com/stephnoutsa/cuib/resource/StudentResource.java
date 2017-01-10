/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Student;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stephnoutsa
 */

@Path("/students/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<Student> getAllStudents() {
        return dbHandler.getAllStudents();
    }
    
    @GET
    @Path("/{dept}/{level}")
    public Student[] getStudents(@PathParam("dept") String dept, @PathParam("level") String level) {
        String department = "";
        try {
            department = java.net.URLDecoder.decode(dept, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbHandler.getStudents(department, level);
    }
    
    @POST
    public Student addStudent(Student student) {
        return dbHandler.addStudent(student);
    }
    
    @PUT
    @Path("/{id}")
    public Student updateStudent(@PathParam("id") String id, Student student) {
        int i = Integer.parseInt(id);
        return dbHandler.updateStudent(i, student);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteStudent(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        dbHandler.deleteStudent(i);
    }
    
    @GET
    @Path("/{id}")
    public Student getStudent(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getStudent(i);
    }
    
    @POST
    @Path("/check")
    public Student studentExists(Student student) {
        return dbHandler.studentExists(student);
    }
    
}
