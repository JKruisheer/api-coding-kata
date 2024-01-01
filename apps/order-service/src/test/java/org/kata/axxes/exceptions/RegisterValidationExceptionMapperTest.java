package org.kata.axxes.exceptions;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisterValidationExceptionMapperTest {

    @Test
    void testRegisterValidationExceptionMapper() {
        RegisterValidationExceptionMapper mapper = new RegisterValidationExceptionMapper();
        RegisterValidationException exception = new RegisterValidationException("Validation failed");

        Response response = mapper.toResponse(exception);

        // Check the response status code
        assertEquals(422, response.getStatus());

        // Check the response entity message
        assertEquals("Validation failed", response.getEntity());
    }
}