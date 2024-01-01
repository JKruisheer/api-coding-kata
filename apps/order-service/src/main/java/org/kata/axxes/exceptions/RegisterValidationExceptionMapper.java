package org.kata.axxes.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class RegisterValidationExceptionMapper implements ExceptionMapper<RegisterValidationException> {
    @Override
    public Response toResponse(RegisterValidationException e) {
        return Response.status(422).entity(e.getMessage()).build();
    }
}
