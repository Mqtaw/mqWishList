package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.User;
import com.mqtaw.mqwishlist.entity.enums.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public List<User> findAllUsers();

    public User findUserByUsername(String username);

    public User findUserById(int id);

    public void save(User user);

    public void deleteById(int id);

    public boolean createUser(User user);
}
