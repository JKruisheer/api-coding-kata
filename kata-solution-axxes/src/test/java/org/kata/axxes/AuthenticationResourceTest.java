package org.kata.axxes;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class AuthenticationResourceTest {

    @Test
    void testLoginSuccess(){
        given()
                .when()
                .get("/login")
                .then()
                .statusCode(200)
                .body(is("Logging in"));
    }
}