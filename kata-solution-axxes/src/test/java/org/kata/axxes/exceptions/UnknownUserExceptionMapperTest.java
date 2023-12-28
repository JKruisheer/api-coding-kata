package org.kata.axxes.exceptions;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UnknownUserExceptionMapperTest {

    @Test
    void testUnknownUserExceptionMapper() {
        UnknownUserExceptionMapper mapper = new UnknownUserExceptionMapper();
        UnknownUserException exception = new UnknownUserException("User not found");

        Response response = mapper.toResponse(exception);

        // Check the response status code
        assertEquals(401, response.getStatus());

        // Check the response entity message
        assertEquals("User not found", response.getEntity());
    }
}