package com.mqtaw.mqwishlist.service;

import com.mqtaw.mqwishlist.entity.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUsers();

    public User findUserById(int id);

    public void save(User user);

    public void deleteById(int id);
}
