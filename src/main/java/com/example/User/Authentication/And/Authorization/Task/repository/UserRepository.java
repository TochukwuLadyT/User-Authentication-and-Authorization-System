package com.example.User.Authentication.And.Authorization.Task.repository;

import com.example.User.Authentication.And.Authorization.Task.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
