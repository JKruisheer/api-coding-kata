package org.kata.axxes.service;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;
import org.kata.axxes.domain.person.Person;
import org.kata.axxes.domain.person.PersonRepository;
import org.kata.axxes.exceptions.UnknownUserException;

import java.time.LocalDateTime;
import java.util.Optional;

@ApplicationScoped
public class AuthenticationService {

    @Inject
    private PersonRepository personRepository;

    public AuthenticationResponse doLogin(LoginRequest loginRequest) {
        Optional<Person> optionalPerson = personRepository.findByUsername(loginRequest.username());
        if (optionalPerson.isEmpty()) {
            throw new UnauthorizedException();
        }
        Person person = optionalPerson.get();
        if (person.getPassword().equals(loginRequest.password())) {
            return new AuthenticationResponse(person.getPersonId());
        }
        throw new UnauthorizedException(String.format("Password for user %s is not correct ", person.getUsername()));
    }

    public AuthenticationResponse doRegister(RegistrationRequest registrationRequest) {
        Person person = new Person();
        person.setPersonName(registrationRequest.name());
        person.setAge(registrationRequest.age());
        person.setAddress(registrationRequest.address());
        person.setPostalCode(registrationRequest.postalCode());
        person.setUsername(registrationRequest.username());
        person.setPassword(registrationRequest.password());
        person.setCreatedBy("Admin");
        person.setCreatedOn(LocalDateTime.now());
        personRepository.persist(person);
        return new AuthenticationResponse(person.getPersonId());
    }

    public Person findPersonById(Long id) throws UnknownUserException {
        return (Person) Person.findByIdOptional(id).orElseThrow(() -> new UnknownUserException("Unknown user"));
    }
}
