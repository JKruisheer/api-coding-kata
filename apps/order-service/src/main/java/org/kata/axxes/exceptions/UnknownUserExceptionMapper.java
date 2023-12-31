package org.kata.axxes.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class UnknownUserExceptionMapper implements ExceptionMapper<UnknownUserException> {
    @Override
    public Response toResponse(UnknownUserException e) {
        return Response.status(401).entity(e.getMessage()).build();
    }
}
