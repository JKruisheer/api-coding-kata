package org.kata.axxes.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RegisterValidationExceptionTest {

    @Test
    void testRegisterValidationException() {
        String errorMessage = "Invalid validation message";

        RegisterValidationException exception = assertThrows(
                RegisterValidationException.class,
                () -> {
                    throw new RegisterValidationException(errorMessage);
                }
        );

        String actualMessage = exception.getMessage();
        assert actualMessage.contains(errorMessage);
    }

}