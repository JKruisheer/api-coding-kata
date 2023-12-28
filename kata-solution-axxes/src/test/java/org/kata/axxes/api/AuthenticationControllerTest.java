package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.kata.axxes.UserRequiredTest;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@Transactional
class AuthenticationControllerTest extends UserRequiredTest {

    @Test
    void testLoginUnknownUserThrowsUnauthorizedException() {
        LoginRequest loginRequest = new LoginRequest("dummy", "dummy");
        given()
                .when()
                .header("Content-Type", "application/json")
                .body(loginRequest)
                .post("/authentication/login")
                .then()
                .statusCode(401);
    }

    @Test
    void testLoginSuccessReturnsPersonId() {
        LoginRequest loginRequest = new LoginRequest(getTestPerson().getUsername(), getTestPerson().getPassword());
        AuthenticationResponse authenticationResponse = given()
                .when()
                .header("Content-Type", "application/json")
                .body(loginRequest)
                .post("/authentication/login")
                .then()
                .statusCode(200)
                .extract().as(AuthenticationResponse.class);

        assertNotNull(authenticationResponse.personId());
    }

    @Test
    void testRegisterSuccessReturnsPersonId() {
        RegistrationRequest registrationRequest = new RegistrationRequest("name", 1, "address", "1000 AA", "username", "password");
        AuthenticationResponse authenticationResponse = given()
                .when()
                .header("Content-Type", "application/json")
                .body(registrationRequest)
                .post("/authentication/register")
                .then()
                .statusCode(200)
                .extract().as(AuthenticationResponse.class);

        assertNotNull(authenticationResponse.personId());
    }


}