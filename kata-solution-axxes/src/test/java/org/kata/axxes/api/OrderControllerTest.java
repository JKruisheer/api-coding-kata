package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.Person;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
@Transactional
class OrderControllerTest {

    @BeforeEach
    void createAPerson() {
        Person person = new Person();
        person.setAge(12);
        person.setPersonName("Jesse");
        person.setUsername("Jesse");
        person.setPassword("avnjaksdnvkajs");
        person.setAddress("Address");
        person.setPostalCode("1000AA");
        person.setCreatedBy("Admin");
        person.setCreatedOn(LocalDateTime.now());
        person.persist();
    }

    @Test
    void testPlaceOrder() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct("ansdvjk");
        orderRequest.setPrice(BigDecimal.TEN);
        orderRequest.setQuantity(5);
        orderRequest.setShippingAddress("123");
        orderRequest.setBillingAddress("123");
        //make something of a get testuser
        orderRequest.setPersonId(1L);

        given()
                .when()
                .header("Content-Type", "application/json")
                .body(orderRequest)
                .post("/orders/create")
                .then()
                .statusCode(200)
                .body(equalTo("Order ansdvjk has been created"));
    }

    @Test
    void testStoreOrderShouldStoreWithCorrectPerson() {

    }
}