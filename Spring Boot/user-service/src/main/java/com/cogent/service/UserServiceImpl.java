package com.cogent.service;

import com.cogent.entity.User;
import com.cogent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

   @Autowired
   private UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllRegularUsers() {
        return userRepository.findByAdminFalse();
    }

    @Override
    public List<User> getAllAdmins() {
        return userRepository.findByAdminTrue();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findById(email).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUserByEmail(String email) {
        userRepository.deleteById(email);
    }
}
