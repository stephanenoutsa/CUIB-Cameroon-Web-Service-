/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stephnoutsa.cuib.resource;

import com.stephnoutsa.cuib.database.MyDBHandler;
import com.stephnoutsa.cuib.model.Token;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author stephnoutsa
 */

@Path("/tokens/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TokenResource {
    
    MyDBHandler dbHandler = new MyDBHandler();
    
    @GET
    public List<Token> getAllTokens() {
        return dbHandler.getAllTokens();
    }
    
    @POST
    public Token addToken(Token token) {
        String value = token.getValue();
        String school = token.getSchool();
        String dept = token.getDepartment();
        String level = token.getLevel();
        
        return dbHandler.addToken(value, school, dept, level);
    }
    
}
