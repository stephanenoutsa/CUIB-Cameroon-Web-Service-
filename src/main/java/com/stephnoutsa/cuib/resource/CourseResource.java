/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Course;
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

@Path("/courses/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @POST
    public Course addCourse(Course course) {
        return dbHandler.addCourse(course);
    }
    
    @GET
    @Path("/{dept}/{level}")
    public Course[] getCourses(@PathParam("dept") String dept, @PathParam("level") String level) {
        String department = "";
        try {
            department = java.net.URLDecoder.decode(dept, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbHandler.getCourses(department, level);
    }
    
    @GET
    @Path("/{id}")
    public Course getCourse(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getCourse(i);
    }
    
    @PUT
    @Path("/{id}")
    public Course updateCourse(@PathParam("id") String id, Course course) {
        int i = Integer.parseInt(id);
        return dbHandler.updateCourse(i, course);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteCourse(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        dbHandler.deleteCourse(i);
    }
    
}
