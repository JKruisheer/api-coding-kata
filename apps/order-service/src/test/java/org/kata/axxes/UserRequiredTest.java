package org.kata.axxes;

import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.kata.axxes.domain.person.Person;
import org.kata.axxes.domain.person.PersonRepository;

import java.time.LocalDateTime;

public class UserRequiredTest {

    public static final String DUMMY_PASSWORD = "123456";

    private Person testPerson;

    @Inject
    private PersonRepository personRepository;

    @BeforeEach
    protected void createPerson() {
        Person person = new Person();
        person.setAge(12);
        person.setPersonName("Jesse");
        person.setUsername("Jesse");
        person.setPassword(DUMMY_PASSWORD);
        person.setAddress("Address");
        person.setPostalCode("1000AA");
        person.setCreatedBy("Admin");
        person.setCreatedOn(LocalDateTime.now());
        personRepository.persist(person);
        testPerson = person;
    }

    public Person getTestPerson() {
        return testPerson;
    }
}
