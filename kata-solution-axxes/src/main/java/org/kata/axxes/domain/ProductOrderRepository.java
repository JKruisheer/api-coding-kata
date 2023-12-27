package org.kata.axxes.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;

@ApplicationScoped
public class ProductOrderRepository implements PanacheRepository<ProductOrder> {

    public BigDecimal calculateTotalSumOfProducts() {
        return (BigDecimal) getEntityManager()
                .createNativeQuery("SELECT SUM(PRODUCT_QUANTITY * PRODUCT_PRICE) FROM PRODUCT_ORDER")
                .getSingleResult();
    }
}
