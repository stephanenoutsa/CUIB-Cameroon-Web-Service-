/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Message;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

@Path("/messages/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<Message> getAllMessages() {
        return dbHandler.getAllMessages();
    }
    
    @POST
    public Message addMessage(Message message) {
        return dbHandler.addMessage(message);
    }
    
    @GET
    @Path("/{id}")
    public Message getMessage(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getMessage(i);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteMessage(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        dbHandler.deleteMessage(i);
    }
    
}
