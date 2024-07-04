package com.example.User.Authentication.And.Authorization.Task.controller;

import com.example.User.Authentication.And.Authorization.Task.model.User;
import com.example.User.Authentication.And.Authorization.Task.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("api/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class.getName());


    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<User>getUserProfile(@RequestHeader("Authorization") String jwt){
        User user = userService.getUserProfile(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>>getAllUsers(@RequestHeader("Authorization") String jwt){
        List<User> user = userService.getAllUsers();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/admin")
    public ResponseEntity<List<User>> getAllUsersByAdmin(@RequestHeader("Authorization") String jwt) {
        List<User> user = userService.getAllUsersByAdmin(jwt);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}



