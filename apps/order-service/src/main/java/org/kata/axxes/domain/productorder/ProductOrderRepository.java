package org.kata.axxes.domain.productorder;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductOrderRepository implements PanacheRepository<ProductOrder> {

    public List<ProductOrderSum> calculateTotalSumOfProducts() {
        List<Object[]> resultList = getEntityManager().createNativeQuery(
                        "SELECT SUM(PRODUCT_QUANTITY * PRODUCT_PRICE) as value, PERSON_ID FROM PRODUCT_ORDER GROUP BY PERSON_ID", Object[].class)
                .getResultList();

        List<ProductOrderSum> productOrderSums = new ArrayList<>();

        for (Object[] row : resultList) {
            BigDecimal value = (BigDecimal) row[0];
            Long personId = ((Number) row[1]).longValue();
            productOrderSums.add(new ProductOrderSum(value, personId));
        }
        return productOrderSums;
    }
}
