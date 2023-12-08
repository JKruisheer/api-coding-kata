package org.kata.axxes.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AuthenticationService {

    public String doLogin(){
        return "Logging in";
    }
}
