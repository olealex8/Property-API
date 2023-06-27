package com.dev.craniumproperty.service;

import com.dev.craniumproperty.dto.SignupDTO;
import com.dev.craniumproperty.dto.request.LoginDTO;
import com.dev.craniumproperty.dto.response.User.ResponseUserDTO;
import com.dev.craniumproperty.dto.response.User.UserDTO;

public interface AuthService {

    ResponseUserDTO createUser(SignupDTO signupDTO);
    ResponseUserDTO login(LoginDTO loginDTO);
    
}
