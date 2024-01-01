package org.kata.axxes.api;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.kata.axxes.service.InventoryService;

@Path("/health")
public class HealthController {


    @ConfigProperty(name = "application.version")
    private String applicationVersion;

    @RestClient
    private InventoryService inventoryService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getVersion() {
        return applicationVersion + " " + inventoryService.getData();
    }


}
