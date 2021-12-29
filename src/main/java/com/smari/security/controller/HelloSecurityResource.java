package com.smari.security.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/security")
public class HelloSecurityResource {

    @GET
    public String getGreeting(){
        return "Hello All";
    }

    @GET
    @Path("/user")
    public String getGreetingUser(){
        return "Hello User";
    }

    @GET
    @Path("/admin")
    public String getGreetingAdmin(){
        return "Hello Admin";
    }
}
