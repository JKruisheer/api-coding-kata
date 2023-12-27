package org.kata.axxes.api;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.kata.axxes.IntegrationTest;
import org.kata.axxes.api.requests.OrderRequest;
import org.kata.axxes.domain.Person;
import org.kata.axxes.domain.ProductOrder;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@Transactional
class OrderControllerTest extends IntegrationTest {
    private static final String ORDER_NAME = "ORDER_NAME";

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
        //TODO MAYBE DELETE THE CURRENT ORDERS?
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

    public OrderRequest createOrderRequest() {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setProduct(ORDER_NAME);
        orderRequest.setPrice(BigDecimal.TEN);
        orderRequest.setQuantity(5);
        orderRequest.setShippingAddress("123");
        orderRequest.setBillingAddress("123");
        orderRequest.setPersonId(getTestPerson().getPersonId());
        return orderRequest;
    }
}