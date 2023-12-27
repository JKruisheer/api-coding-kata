package org.kata.axxes.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PERSON")
public class Person extends PanacheEntityBase {

    @Id
    @SequenceGenerator(
            name = "personSequence",
            sequenceName = "person_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "personSequence")
    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "PERSON_NAME", nullable = false)
    private String personName;

    @Column(name = "PERSON_AGE", nullable = false)
    private int age;

    @Column(name = "PERSON_ADDRESS", nullable = false)
    private String address;

    @Column(name = "PERSON_POSTAL_CODE", nullable = false)
    private String postalCode;

    @Column(name = "PERSON_USERNAME", nullable = false)
    private String username;

    @Column(name = "PERSON_PASSWORD", nullable = false)
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductOrder> productOrders;

    @Column(name = "CREATED_BY", nullable = false)
    private String createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "LAST_UPDATED_BY", nullable = false)
    private String lastUpdatedBy;

    @Column(name = "LAST_UPDATED_ON", nullable = false)
    private LocalDateTime lastUpdatedOn;

    public Long getPersonId() {
        return personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public LocalDateTime getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setLastUpdatedOn(LocalDateTime lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    @Override
    public void persist() {
        this.lastUpdatedOn = LocalDateTime.now();
        this.lastUpdatedBy = "Admin";
        super.persist();
    }
}
