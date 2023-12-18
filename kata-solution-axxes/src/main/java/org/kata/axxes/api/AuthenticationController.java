package org.kata.axxes.api;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;
import org.kata.axxes.exceptions.InvalidRequestException;
import org.kata.axxes.exceptions.RegisterValidationException;
import org.kata.axxes.service.AuthenticationService;

@Path("/authentication")
public class AuthenticationController {

    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/login")
    public AuthenticationResponse doLogin(LoginRequest loginRequest) {
        return authenticationService.doLogin(loginRequest);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/register")
    @Transactional
    public AuthenticationResponse doRegister(RegistrationRequest registrationRequest) {
        try {
            validateIncomingRequest(registrationRequest);
            return authenticationService.doRegister(registrationRequest);
        } catch (InvalidRequestException e) {
            throw new RegisterValidationException(e.getMessage());
        }
    }

    private void validateIncomingRequest(RegistrationRequest registrationRequest) {
        //todo
    }
}
