package org.kata.axxes.service;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/inventory")
@RegisterRestClient(configKey = "inventory-api")
public interface InventoryService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    String getData();
}
