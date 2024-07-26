package com.cogent.service;

import com.cogent.entity.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    List<User> getAllRegularUsers();

    List<User> getAllAdmins();

    User getUserByEmail(String email);

    User saveUser(User user);

    void deleteUserByEmail(String email);

}
