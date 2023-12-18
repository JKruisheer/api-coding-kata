package org.kata.axxes.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

import java.util.Optional;

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

    @Column(name = "PERSON_NAME")
    private String personName;

    @Column(name = "PERSON_AGE")
    private int age;

    @Column(name = "PERSON_ADDRESS")
    private String address;

    @Column(name = "PERSON_POSTAL_CODE")
    private String postalCode;

    @Column(name = "PERSON_USERNAME")
    private String username;

    @Column(name = "PERSON_PASSWORD")
    private String password;

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

    public static Optional<Person> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

}
