package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
class InventoryControllerTest {

    @Test
    void testHappyPath() {
        given()
                .when()
                .get("/inventory")
                .then()
                .statusCode(200)
                .body(equalTo("Alive"));
    }
}