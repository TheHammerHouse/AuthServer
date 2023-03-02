package com.example.authserver.dtos;

import lombok.Data;

@Data
public final class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String userName;
    private String streetName;
    private String streetNumber;
    private String postalCode;
    private String province;
    private String country;

}
