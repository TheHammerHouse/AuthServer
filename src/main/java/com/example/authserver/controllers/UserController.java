package com.example.authserver.controllers;

import com.example.authserver.dtos.CreateUserDTO;
import com.example.authserver.dtos.UserDTO;
import com.example.authserver.entities.User;
import com.example.authserver.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {
        User user = userService.createUser(createUserDTO);

        UserDTO userDTO = new UserDTO();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, userDTO);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "")
    public ResponseEntity<UserDTO> getUserByUserName(@RequestParam String userName) {
        User user = userService.getUserByUserName(userName);

        UserDTO userDTO = new UserDTO();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, userDTO);

        return ResponseEntity.ok(userDTO);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDTO> getUserByID(@PathVariable(value = "userId") int userId) {
        User user = userService.getUserById(userId);

        UserDTO userDTO = new UserDTO();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(user, userDTO);

        return ResponseEntity.ok(userDTO);
    }

}
