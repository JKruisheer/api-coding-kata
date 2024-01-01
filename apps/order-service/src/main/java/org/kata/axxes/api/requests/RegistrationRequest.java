package org.kata.axxes.api.requests;

public record RegistrationRequest(String name, int age, String address, String postalCode, String username,
                                  String password) {
}
