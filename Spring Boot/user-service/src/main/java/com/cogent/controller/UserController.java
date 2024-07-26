package com.cogent.controller;

import com.cogent.entity.User;
import com.cogent.service.UserService;
import com.cogent.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userServiceImpl;

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userServiceImpl.getAllUsers();
    }

    @GetMapping("/admin")
    public List<User> getAdminUsers() {
        return userServiceImpl.getAllAdmins();
    }

    @GetMapping("/standard")
    public List<User> getStandardUsers() {
        return userServiceImpl.getAllRegularUsers();
    }

    @GetMapping("/{userEmail}")
    public User getUser(@PathVariable String userEmail) {
        User user = userServiceImpl.getUserByEmail(userEmail);

        if (user == null) {
            System.out.println("Unable to find user with email " + userEmail);
        }

        return user;
    }

    @PostMapping("/add")
    public User addUser(@RequestBody User user) {
        return userServiceImpl.saveUser(user);
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User user) {
        User foundUser = userServiceImpl.getUserByEmail(user.getEmail());
        if (foundUser != null) {
            return userServiceImpl.saveUser(user);
        } else {
            return null;
        }
    }
}
