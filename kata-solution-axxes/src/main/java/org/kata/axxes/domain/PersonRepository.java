package org.kata.axxes.domain;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class PersonRepository implements PanacheRepository<Person> {

    public Optional<Person> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }

    @Override
    public void persist(Person person) {
        person.setLastUpdatedOn(LocalDateTime.now());
        person.setLastUpdatedBy("Admin");
        PanacheRepository.super.persist(person);
    }
}
