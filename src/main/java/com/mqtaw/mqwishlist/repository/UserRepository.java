package com.mqtaw.mqwishlist.repository;

import com.mqtaw.mqwishlist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
