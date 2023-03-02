package com.example.authserver.services;

import com.example.authserver.dtos.CreateUserDTO;
import com.example.authserver.dtos.UserDTO;
import com.example.authserver.entities.User;
import com.example.authserver.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {
        Optional<User> userOptional = userRepository.findByUserName(createUserDTO.getUserName());
        if (userOptional.isPresent()) {
            // TODO Add more granular exceptions.
            throw new RuntimeException("User already exists");
        }

        User user = new User();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(createUserDTO, user);

        // TODO Add logic for password hashing and salting.

        userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        modelMapper.map(user, userDTO);

        return userDTO;
    }
}
