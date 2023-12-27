package org.kata.axxes.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "PRODUCT_ORDER")
public class ProductOrder extends PanacheEntity {

    @Column(name = "PRODUCT_NAME", nullable = false)
    private String productName;

    @Column(name = "PRODUCT_QUANTITY", nullable = false)
    private Integer quantity;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "PRODUCT_BILLING_ADDRESS", nullable = false)
    private String billingAddress;

    @Column(name = "PRODUCT_SHIPPING_ADDRESS", nullable = false)
    private String shippingAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID")
    private Person person;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
