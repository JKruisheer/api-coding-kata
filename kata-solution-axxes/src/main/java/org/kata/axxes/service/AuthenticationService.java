package org.kata.axxes.service;

import io.quarkus.security.UnauthorizedException;
import jakarta.enterprise.context.ApplicationScoped;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;
import org.kata.axxes.domain.Person;

import java.util.Optional;

@ApplicationScoped
public class AuthenticationService {

    public AuthenticationResponse doLogin(LoginRequest loginRequest) {
        Optional<Person> optionalPerson = Person.findByUsername(loginRequest.username());
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
        return null;
    }
}
