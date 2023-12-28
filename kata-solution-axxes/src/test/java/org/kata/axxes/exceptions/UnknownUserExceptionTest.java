package org.kata.axxes.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UnknownUserExceptionTest {

    @Test
    void testUnknownUserException() {
        String errorMessage = "Invalid validation message";

        UnknownUserException exception = assertThrows(
                UnknownUserException.class,
                () -> {
                    throw new UnknownUserException(errorMessage);
                }
        );

        String actualMessage = exception.getMessage();
        assert actualMessage.contains(errorMessage);
    }

}