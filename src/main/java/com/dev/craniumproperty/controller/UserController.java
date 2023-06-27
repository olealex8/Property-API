package com.dev.craniumproperty.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.craniumproperty.dto.SignupDTO;
import com.dev.craniumproperty.dto.request.LoginDTO;
import com.dev.craniumproperty.dto.response.User.ResponseUserDTO;
import com.dev.craniumproperty.dto.response.User.UserDTO;
import com.dev.craniumproperty.entity.AgentEntity;
import com.dev.craniumproperty.service.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthService authService;
    
    /** Login **/
    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody LoginDTO loginDto, HttpServletRequest request) {
        ResponseUserDTO responseUserDto = authService.login(loginDto);
        return new ResponseEntity<>(responseUserDto, HttpStatus.OK);
    }

    /** Signup **/
    @PostMapping("/sign-up")
    public ResponseEntity signupUser(@RequestBody SignupDTO signupDTO) {
       ResponseUserDTO responseUserDto = authService.createUser(signupDTO);
       return new ResponseEntity<>(responseUserDto, HttpStatus.CREATED);
    }
}
