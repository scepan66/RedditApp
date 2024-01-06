package com.example.redditwannabe.services;

import com.example.redditwannabe.dtos.UserDTO;
import com.example.redditwannabe.models.User;
import com.example.redditwannabe.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void createNewUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getEmail(), userDTO.getPassword());
        saveUser(user);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUserFromLogin(User user) {
        Optional<User> userFound = userRepository.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        return userFound.orElse(null);
    }
}
