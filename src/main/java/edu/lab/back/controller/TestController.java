package edu.lab.back.controller;

import javax.servlet.annotation.WebServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path(value = "/test")
@WebServlet
public class TestController {

    @GET
    @Produces("application/json")
    public String test() {
        return "{\"test\":1}";
    }

}
