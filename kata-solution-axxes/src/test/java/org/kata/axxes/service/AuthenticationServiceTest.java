package org.kata.axxes.service;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.security.UnauthorizedException;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.kata.axxes.api.requests.AuthenticationResponse;
import org.kata.axxes.api.requests.LoginRequest;
import org.kata.axxes.api.requests.RegistrationRequest;
import org.kata.axxes.domain.Person;
import org.kata.axxes.domain.PersonRepository;
import org.kata.axxes.exceptions.UnknownUserException;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@QuarkusTest
class AuthenticationServiceTest {
    public static final String USERNAME = "TestUsername";

    @Inject
    private AuthenticationService authenticationService;

    @InjectMock
    private PersonRepository personRepository;

    @Test
    void doLoginShouldThrowWhenUsernameNotFound() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        when(personRepository.findByUsername(USERNAME)).thenReturn(Optional.empty());

        assertThrows(UnauthorizedException.class, () -> authenticationService.doLogin(loginRequest));
    }

    @Test
    void doLoginShouldThrowUnauthorizedExceptionWhenPasswordsDoNotMatch() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        Person person = createMockPerson("www");

        when(personRepository.findByUsername(USERNAME)).thenReturn(Optional.of(person));

        assertThrows(UnauthorizedException.class, () -> authenticationService.doLogin(loginRequest));
    }

    @Test
    void doLoginShouldReturnLoginResponseOnSuccess() {
        PanacheMock.mock(Person.class);

        LoginRequest loginRequest = new LoginRequest(USERNAME, "ww");

        Person person = createMockPerson("ww");

        when(personRepository.findByUsername(USERNAME)).thenReturn(Optional.of(person));

        AuthenticationResponse response = authenticationService.doLogin(loginRequest);

        assertEquals(1, response.personId());
    }

    @Test
    @Transactional
    void doRegisterShouldPersistPerson() {
        RegistrationRequest registrationRequest = new RegistrationRequest("name", 1, "address", "1000 AA", "username", "password");

        authenticationService.doRegister(registrationRequest);

        ArgumentCaptor<Person> argumentCaptor = ArgumentCaptor.forClass(Person.class);
        verify(personRepository, times(1)).persist(argumentCaptor.capture());

        Person capturedPerson = argumentCaptor.getValue();
        assertEquals(capturedPerson.getPersonName(), registrationRequest.name());
        assertEquals(capturedPerson.getAge(), registrationRequest.age());
        assertEquals(capturedPerson.getAddress(), registrationRequest.address());
        assertEquals(capturedPerson.getPostalCode(), registrationRequest.postalCode());
        assertEquals(capturedPerson.getUsername(), registrationRequest.username());
        assertEquals(capturedPerson.getPassword(), registrationRequest.password());
    }

    @Test
    void findPersonById() throws UnknownUserException {
        PanacheMock.mock(Person.class);

        Person person = createMockPerson("ww");

        when(Person.findByIdOptional(person.getPersonId())).thenReturn(Optional.of(person));

        Person optionalPerson = authenticationService.findPersonById(person.getPersonId());

        assertNotNull(optionalPerson);
    }

    @Test
    void findPersonByIdShouldThrowUnknownUserException() {
        PanacheMock.mock(Person.class);

        Person person = createMockPerson("ww");

        assertThrows(UnknownUserException.class, () ->
                authenticationService.findPersonById(person.getPersonId()));
    }

    private Person createMockPerson(String password) {
        Person person = mock(Person.class);
        when(person.getUsername()).thenReturn(USERNAME);
        when(person.getPassword()).thenReturn(password);
        when(person.getPersonId()).thenReturn(1L);
        return person;
    }


}