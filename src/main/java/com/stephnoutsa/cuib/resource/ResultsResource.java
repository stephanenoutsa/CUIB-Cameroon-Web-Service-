/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Results;
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

@Path("/results/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResultsResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @POST
    public Results addResults(Results results) {
        return dbHandler.addResults(results);
    }
    
    @GET
    @Path("/{year}/{semester}/{matricule}")
    public Results getResults(@PathParam("year") String year, @PathParam("semester") String semester, @PathParam("matricule") String matricule) {
        String yr = "";
        String sem = "";
        String mat = "";
        try {
            yr = java.net.URLDecoder.decode(getYear(year), "UTF-8");
            sem = java.net.URLDecoder.decode(semester, "UTF-8");
            mat = java.net.URLDecoder.decode(matricule, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dbHandler.getResults(yr, sem, mat);
    }
    
    // Get actual academic year
    public String getYear(String year) {
        String[] yrArray = year.split("-");
        
        String yr = "";
        for (int i = 0; i < yrArray.length; i++) {
            yr += yrArray[i];
            if (i != (yrArray.length - 1)) {
                yr += "/";
            }
        }
        
        return yr;
    }
    
}
