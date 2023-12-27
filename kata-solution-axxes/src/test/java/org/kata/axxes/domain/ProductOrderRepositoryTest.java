package org.kata.axxes.domain;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
@Transactional
class ProductOrderRepositoryTest {

    @Inject
    private ProductOrderRepository productOrderRepository;

    @BeforeEach
    void insertProductOrders() {
        productOrderRepository.deleteAll();
        ProductOrder productOrder = new ProductOrder();
        productOrder.setProductName("name");
        productOrder.setQuantity(2);
        productOrder.setPrice(BigDecimal.TEN);
        productOrder.setShippingAddress("Shipping");
        productOrder.setBillingAddress("billing");
        productOrderRepository.persist(productOrder);
    }

    @Test
    void calculateShouldReturnCorrectValues() {
        BigDecimal value = productOrderRepository.calculateTotalSumOfProducts();
        System.out.println(value);
        assertTrue(value.compareTo(new BigDecimal(20)) == 0);
    }
}