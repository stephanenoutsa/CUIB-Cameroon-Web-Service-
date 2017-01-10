/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Lecturer;
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

@Path("/lecturers/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LecturerResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<Lecturer> getAllLecturers() {
        return dbHandler.getAllLecturers();
    }
    
    @POST
    public Lecturer addLecturer(Lecturer lecturer) {
        return dbHandler.addLecturer(lecturer);
    }   
    
    @PUT
    @Path("/{id}")
    public Lecturer updateLecturer(@PathParam("id") String id, Lecturer lecturer) {
        int i = Integer.parseInt(id);
        return dbHandler.updateLecturer(i, lecturer);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteLecturer(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        dbHandler.deleteLecturer(i);
    }
    
    @GET
    @Path("/{id}")
    public Lecturer getLecturer(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getLecturer(i);
    }
    
    @GET
    @Path("/{dept}/{level}")
    public Lecturer[] getLecturers(@PathParam("dept") String dept, @PathParam("level") String level) {
        String department = "";
        try {
            department = java.net.URLDecoder.decode(dept, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbHandler.getLecturers(department, level);
    }
    
}
