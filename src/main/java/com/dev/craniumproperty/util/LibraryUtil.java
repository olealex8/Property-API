package com.dev.craniumproperty.util;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.dev.craniumproperty.entity.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class LibraryUtil{
    public String generateJwtToken(UserEntity user) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, ConstantUtil.API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + ConstantUtil.TOKEN_VALIDTY))
                .claim("userId", user.getId())
                .claim("userEmail", user.getEmail()).compact();
        return token;
    }

    // public boolean verify(String token) throws Exception {
    //     try {
    //         Jwts.parserBuilder()
    //         .setSigningKey(ConstantUtil.API_SECRET_KEY)
    //         .build()
    //         .parseClaimsJws(token)
    //         .getBody();
            
    //     } catch (Exception e) {
    //         throw new Exception("Invalid Token"); 
    //     }
    //     return true;
    // }

    // public Boolean validateToken(String token, UserDetails userDetails) {
    //     final String username = extractUsername(token);
    //     return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    // }
}
