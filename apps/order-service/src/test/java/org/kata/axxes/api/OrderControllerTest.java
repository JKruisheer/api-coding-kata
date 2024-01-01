package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.axxes.UserRequiredTest;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.person.Person;
import org.kata.axxes.domain.productorder.ProductOrder;
import org.kata.axxes.domain.productorder.ProductOrderRepository;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Transactional
class OrderControllerTest extends UserRequiredTest {
    private static final String ORDER_NAME = "ORDER_NAME";

    @Inject
    private ProductOrderRepository productOrderRepository;

    @BeforeEach
    void clearCurrentOrders() {
        productOrderRepository.deleteAll();
    }

    @Test
    void testPlaceOrder() {
        given()
                .when()
                .header("Content-Type", "application/json")
                .body(createOrderRequest())
                .post("/orders/create")
                .then()
                .statusCode(200)
                .body(equalTo(String.format("Order %s has been created", ORDER_NAME)));
    }

    @Test
    void testStoreOrderShouldStoreWithCorrectPerson() {
        given()
                .when()
                .header("Content-Type", "application/json")
                .body(createOrderRequest())
                .post("/orders/create")
                .then()
                .statusCode(200)
                .body(equalTo(String.format("Order %s has been created", ORDER_NAME)));

        Person person = Person.findById(getTestPerson().getPersonId());
        List<ProductOrder> orders = person.getProductOrders();
        assertEquals(1, orders.size());
        assertEquals(ORDER_NAME, orders.get(0).getProductName());
    }

    @Test
    void fetchReportReturnWithNoOrdersReturnEmpty() {
        given()
                .when()
                .header("Content-Type", "application/json")
                .get("/orders/report")
                .then()
                .log().body()
                .statusCode(200)
                .body(equalTo("[]"));
    }

    @Test
    void fetchReportReturnsWithOrders() {
        testPlaceOrder();
        given()
                .when()
                .header("Content-Type", "application/json")
                .get("/orders/report")
                .then()
                .log().body()
                .statusCode(200)
                .body("[0].value", equalTo(645600.20f))
                .body("[0].personId", notNullValue());
    }

    public OrderRequest createOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct(ORDER_NAME);
        orderRequest.setPrice(new BigDecimal("129120.04"));
        orderRequest.setQuantity(5);
        orderRequest.setShippingAddress("123");
        orderRequest.setBillingAddress("123");
        orderRequest.setPersonId(getTestPerson().getPersonId());
        return orderRequest;
    }
}