package com.example.redditwannabe.services;

import com.example.redditwannabe.dtos.UserDTO;
import com.example.redditwannabe.models.User;

public interface UserService {
    void saveUser(User user);

    void createNewUser(UserDTO userDTO);

    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    User getUserFromLogin(User user);
}
