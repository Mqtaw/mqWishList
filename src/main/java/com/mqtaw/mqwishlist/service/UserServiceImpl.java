package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(int id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(null);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}
