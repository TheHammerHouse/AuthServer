package com.example.authserver.utilities;

import org.springframework.stereotype.Component;

@Component
public class JWTUtil {
    public JWTUtil() {

    }

    public String generateAuthToken(String userName) {
        // TODO implement logic to generate auth token.
        return "some token";
    }
}
