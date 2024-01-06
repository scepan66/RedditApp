package com.example.redditwannabe.repositories;

import com.example.redditwannabe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    User getUserByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
