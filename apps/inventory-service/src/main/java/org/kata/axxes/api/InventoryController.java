package org.kata.axxes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/inventory")
public class InventoryController {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Alive from the other service";
    }
}
