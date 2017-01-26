/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Payment;
import java.util.List;
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

@Path("/payments/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<Payment> getAllPayments() {
        return dbHandler.getAllPayments();
    }
    
    @POST
    public Payment addPayment(Payment payment) {
        return dbHandler.addPayment(payment);
    }
    
    @GET
    @Path("/{id}")
    public Payment getPayment(@PathParam("id") String id) {
        int i = Integer.parseInt(id);
        return dbHandler.getPayment(i);
    }
    
}
