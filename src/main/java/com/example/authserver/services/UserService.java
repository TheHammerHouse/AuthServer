package com.example.authserver.services;

import com.example.authserver.dtos.CreateUserDTO;
import com.example.authserver.entities.User;
import com.example.authserver.exceptions.AccountExistsException;
import com.example.authserver.exceptions.UserNotFoundException;
import com.example.authserver.repositories.IUserRepository;
import com.example.authserver.utilities.AuthUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final IUserRepository userRepository;
    private final AuthUtil authUtil;

    @Autowired
    public UserService(IUserRepository userRepository, AuthUtil authUtil) {
        this.userRepository = userRepository;
        this.authUtil = authUtil;
    }

    public User createUser(CreateUserDTO createUserDTO) {
        Optional<User> userOptional = userRepository.findByUserName(createUserDTO.getUserName());
        if (userOptional.isPresent()) {
            throw new AccountExistsException("A user with this username already exists.");
        }

        User user = new User();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(createUserDTO, user);

        String salt = authUtil.generateSalt();
        String hashedAndSaltedPassword = authUtil.hashAndSaltPassword(createUserDTO.getPassword(), salt);

        user.setSalt(salt);
        user.setPassword(hashedAndSaltedPassword);

        userRepository.save(user);

        return user;
    }

    public User getUserByUserName(String userName) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Could not find user with that username.");
        }

        return userOptional.get();
    }

    public User getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("Could not find user with that ID.");
        }

        return userOptional.get();
    }
}
