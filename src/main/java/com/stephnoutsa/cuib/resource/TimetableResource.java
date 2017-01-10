/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Timetable;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stephnoutsa
 */

@Path("/timetables/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TimetableResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @POST
    public Timetable addTimetable(Timetable timetable) {
        return dbHandler.addTimetable(timetable);
    }
    
    @GET
    @Path("/{dept}/{level}")
    public Timetable getTimetable(@PathParam("dept") String dept, @PathParam("level") String level) {
        String department = "";
        try {
            department = java.net.URLDecoder.decode(dept, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbHandler.getTimetable(department, level);
    }
    
}
