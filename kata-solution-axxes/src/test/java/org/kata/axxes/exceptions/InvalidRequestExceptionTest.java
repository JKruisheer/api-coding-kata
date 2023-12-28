package org.kata.axxes.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class InvalidRequestExceptionTest {

    @Test
    void testInvalidRequestException() {
        String errorMessage = "Invalid request message";

        InvalidRequestException exception = assertThrows(
                InvalidRequestException.class,
                () -> {
                    throw new InvalidRequestException(errorMessage);
                }
        );

        String actualMessage = exception.getMessage();
        assert actualMessage.contains(errorMessage);
    }
}