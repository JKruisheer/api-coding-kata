package org.kata.axxes;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kata.axxes.service.AuthenticationService;

@Path("/login")
public class AuthenticationResource {

    @Inject
    private AuthenticationService authenticationService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doLogin() {
        return authenticationService.doLogin();
    }


    public void register(){
        //TODO IMPLEMENT
    }
}
