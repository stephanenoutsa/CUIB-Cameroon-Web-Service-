/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.User;
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

@Path("/users/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<User> getAllUsers() {
        return dbHandler.getAllUsers();
    }
    
    @POST
    public User addUser(User user) {
        return dbHandler.onlyAddUser(user);
    }
    
    @PUT
    @Path("/{id}")
    public User updateUser(@PathParam("id") String id, User user) {
        int i = Integer.parseInt(id);
        return dbHandler.updateUser(i, user);
    }
    
    @DELETE
    @Path("/{id}")
    public void deleteUser(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        dbHandler.deleteUser(i);
    }
    
    @GET
    @Path("/{id}")
    public User getUser(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getUser(i);
    }
    
    @PUT
    @Path("/app/{email}")
    public User updateAppUser(@PathParam("email") String email, User user) {
        return dbHandler.updateAppUser(email, user);
    }
    
    @POST
    @Path("/check/login")
    public User loginCheck(User user) {
        return dbHandler.loginCheck(user.getEmail(), user.getPassword());
    }
    
}
