package com.example.authserver.utilities;

import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Component
public class JWTUtil {
    public JWTUtil() {

    }

    public String generateAuthToken(String userName) {
        return Jwts.builder()
                .claim("userName", userName)
                .setSubject("auth token")
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .compact();
    }
}
