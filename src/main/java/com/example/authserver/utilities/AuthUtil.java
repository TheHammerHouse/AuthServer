package com.example.authserver.utilities;

import org.springframework.stereotype.Component;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Component
public class AuthUtil {

    public String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[30];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashAndSaltPassword(String password, String salt) {
        char[] passwordCharArray = password.toCharArray();

        int ITERATIONS = 100000;
        int KEY_LENGTH = 256;

        KeySpec keySpec = new PBEKeySpec(
                passwordCharArray,
                Base64.getDecoder().decode(salt),
                ITERATIONS,
                KEY_LENGTH
        );

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] encodedHash = factory.generateSecret(keySpec).getEncoded();

            return Base64.getEncoder().encodeToString(encodedHash);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            // TODO Add more granular exception.
            throw new RuntimeException(e.getMessage());
        }
    }
}
