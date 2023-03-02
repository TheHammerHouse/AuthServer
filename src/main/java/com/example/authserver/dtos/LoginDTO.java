package com.example.authserver.dtos;

import lombok.Data;

@Data
public final class LoginDTO {
    private String userName;
    private String password;
}
