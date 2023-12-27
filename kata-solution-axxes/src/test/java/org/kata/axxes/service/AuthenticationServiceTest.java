package org.kata.axxes.service;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;
import org.kata.axxes.domain.Person;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@QuarkusTest
class AuthenticationServiceTest {
    public static final String USERNAME = "TestUsername";

    @Inject
    private AuthenticationService authenticationService;

    @Test
    void doLoginShouldThrowWhenUsernameNotFound() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        when(Person.findByUsername(USERNAME)).thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> authenticationService.doLogin(loginRequest));
    }

    @Test
    void doLoginShouldThrowUnauthorizedExceptionWhenPasswordsDoNotMatch() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        Person person = createMockPerson("www");

        when(Person.findByUsername(USERNAME)).thenReturn(Optional.of(person));

        assertThrows(UnauthorizedException.class, () -> authenticationService.doLogin(loginRequest));
    }

    @Test
    void doLoginShouldReturnLoginResponseOnSuccess() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        Person person = createMockPerson("ww");

        when(Person.findByUsername(USERNAME)).thenReturn(Optional.of(person));

        AuthenticationResponse response = authenticationService.doLogin(loginRequest);

        assertEquals(1, response.personId());
    }

    @Test
    @Transactional
    void doRegisterShouldPersistPerson() {
        RegistrationRequest registrationRequest = new RegistrationRequest("name", 1, "address", "1000 AA", "username", "password");

        AuthenticationResponse authenticationResponse = authenticationService.doRegister(registrationRequest);

        assertNotNull(authenticationResponse.personId());
    }

    @Test
    @Transactional
    void doRegisterShouldMapEverythingCorrectToDatabase() {
        RegistrationRequest registrationRequest = new RegistrationRequest("name", 1, "address", "1000 AA", "username", "password");

        AuthenticationResponse authenticationResponse = authenticationService.doRegister(registrationRequest);

        Person person = Person.findById(authenticationResponse.personId());
        assertEquals(registrationRequest.name(), person.getPersonName());
        assertEquals(registrationRequest.age(), person.getAge());
        assertEquals(registrationRequest.address(), person.getAddress());
        assertEquals(registrationRequest.postalCode(), person.getPostalCode());
        assertEquals(registrationRequest.username(), person.getUsername());
        assertEquals(registrationRequest.password(), person.getPassword());
        assertEquals("Admin", person.getCreatedBy());
    }

    private Person createMockPerson(String password) {
        Person person = mock(Person.class);
        when(person.getUsername()).thenReturn(USERNAME);
        when(person.getPassword()).thenReturn(password);
        when(person.getPersonId()).thenReturn(1L);
        return person;
    }


}