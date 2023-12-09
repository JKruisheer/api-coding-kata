package org.kata.axxes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health")
public class HealthController {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/version")
    public String getVersion() {
        return "0.0.1";
    }
}
