package org.kata.axxes.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.kata.axxes.UserRequiredTest;
import org.kata.axxes.domain.person.Person;
import org.kata.axxes.domain.productorder.ProductOrder;
import org.kata.axxes.domain.productorder.ProductOrderRepository;
import org.kata.axxes.domain.productorder.ProductOrderSum;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Transactional
class ProductOrderRepositoryTest extends UserRequiredTest {

    @Inject
    private ProductOrderRepository productOrderRepository;

    @BeforeEach
    void insertProductOrders() {
        Person person = new Person();
        person.setPersonId(1L);
        productOrderRepository.deleteAll();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductName("name");
        productOrder.setQuantity(2);
        productOrder.setPrice(BigDecimal.TEN);
        productOrder.setShippingAddress("Shipping");
        productOrder.setBillingAddress("billing");
        productOrder.setPerson(person);
        productOrderRepository.persist(productOrder);
    }

    @Test
    void calculateShouldReturnCorrectValues() {
        List<ProductOrderSum> productOrderSums = productOrderRepository.calculateTotalSumOfProducts();
        assertEquals(1, productOrderSums.size());
        assertTrue(productOrderSums.get(0).value().compareTo(BigDecimal.valueOf(20)) == 0);
    }
}