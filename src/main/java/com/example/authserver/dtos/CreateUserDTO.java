package com.example.authserver.dtos;

import lombok.Data;

@Data
public class CreateUserDTO {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String city;
    private String province;
    private String country;

}
