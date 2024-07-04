package com.example.User.Authentication.And.Authorization.Task.config;


import io.jsonwebtoken.JwtException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.*;

@Component
public class JwtProvider {

    static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    public static String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email", auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return jwt;
    }


    public static String getEmailFromJwtToken(String jwt){
        jwt = jwt.substring(7);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(jwt)
                .getBody();

        return (String) claims.get("email");
    }

    public static String getRoleFromJwtToken(String jwt) {
        jwt = jwt.substring(7);
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();

            return (String) claims.get("role");
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Error parsing JWT token: " + e.getMessage(), e);
        }
    }


    public static String populateAuthorities(Collection<? extends GrantedAuthority> collection){
        Set<String> auths = new HashSet<>();
        for(GrantedAuthority authority:collection){
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }

}
