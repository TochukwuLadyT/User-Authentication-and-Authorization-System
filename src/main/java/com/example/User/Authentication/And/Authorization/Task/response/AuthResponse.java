package com.example.User.Authentication.And.Authorization.Task.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private Boolean status;
}
