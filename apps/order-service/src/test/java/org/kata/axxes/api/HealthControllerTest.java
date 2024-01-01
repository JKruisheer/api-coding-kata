package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class HealthControllerTest {

    @ConfigProperty(name = "application.version")
    private String applicationVersion;

    @Test
    void healthApiShouldReturnVersionNumber() {
        given()
                .when()
                .get("/health")
                .then().statusCode(200)
                .body(equalTo(applicationVersion));
    }
}