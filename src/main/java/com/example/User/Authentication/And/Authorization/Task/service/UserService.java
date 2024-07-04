package com.example.User.Authentication.And.Authorization.Task.service;

import com.example.User.Authentication.And.Authorization.Task.model.User;

import java.util.List;

public interface UserService {
    User getUserProfile(String jwt);

    List<User>getAllUsers();

    List<User> getAllUsersByAdmin(String jwt);


}
