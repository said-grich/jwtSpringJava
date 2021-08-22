package com.example.jwtSpringJava.service;

import com.example.jwtSpringJava.entity.Role;
import com.example.jwtSpringJava.entity.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username,String roleName);
    User getUser(String username);
    List<User> getUsers();
    int changePassword(String username ,String oldPassword,String newPassword);

}

