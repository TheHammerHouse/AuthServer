package com.example.authserver.services;

import com.example.authserver.dtos.LoginDTO;
import com.example.authserver.dtos.TokenDTO;
import com.example.authserver.entities.User;
import com.example.authserver.utilities.AuthUtil;
import com.example.authserver.utilities.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JWTUtil jwtUtil;
    private final UserService userService;

    private final AuthUtil authUtil;

    @Autowired
    public AuthService(JWTUtil jwtUtil, AuthUtil authUtil, UserService userService) {
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.authUtil = authUtil;
    }

    public TokenDTO login(LoginDTO loginDTO) {
        String userName = loginDTO.getUserName();
        User user = userService.getUserByUserName(userName);

        String salt = user.getSalt();
        String expectedHashedPassword = user.getPassword();
        String hashedPassword = authUtil.hashAndSaltPassword(loginDTO.getPassword(), salt);

        if (!hashedPassword.equals(expectedHashedPassword)) {
            // TODO Add more granular exceptions.
            throw new RuntimeException("Invalid Credentials");
        }
        String authToken = jwtUtil.generateAuthToken(userName);

        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(authToken);

        return tokenDTO;
    }
}
