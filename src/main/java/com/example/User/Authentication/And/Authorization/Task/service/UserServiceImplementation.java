package com.example.User.Authentication.And.Authorization.Task.service;

import com.example.User.Authentication.And.Authorization.Task.config.JwtProvider;
import com.example.User.Authentication.And.Authorization.Task.model.User;
import com.example.User.Authentication.And.Authorization.Task.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@Service
public class UserServiceImplementation implements UserService{
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserProfile(String jwt) {
        String email = JwtProvider.getEmailFromJwtToken(jwt);
        return  userRepository.findByEmail(email);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsersByAdmin(String jwt) {
        String requestRole = JwtProvider.getRoleFromJwtToken(jwt);
        logger.info("Requester role: {}", requestRole);

        if (requestRole == null || !requestRole.equals("ROLE_ADMIN")) {
            throw new AccessDeniedException("Only Admin can view all users");
        }

        List<User> users = userRepository.findAll();
        logger.info("Number of users found: {}", users.size());
        return users;
    }

}
