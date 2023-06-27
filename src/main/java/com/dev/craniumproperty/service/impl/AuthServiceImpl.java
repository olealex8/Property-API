package com.dev.craniumproperty.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dev.craniumproperty.dto.SignupDTO;
import com.dev.craniumproperty.dto.request.LoginDTO;
import com.dev.craniumproperty.dto.response.User.ResponseLoginDTO;
import com.dev.craniumproperty.dto.response.User.ResponseUserDTO;
import com.dev.craniumproperty.dto.response.User.UserDTO;
import com.dev.craniumproperty.entity.UserEntity;
import com.dev.craniumproperty.exception.ResultInternalServerErrorException;
import com.dev.craniumproperty.exception.ResultNotFoundException;
import com.dev.craniumproperty.repository.UserRepository;
import com.dev.craniumproperty.service.AuthService;
import com.dev.craniumproperty.util.ConstantUtil;
import com.dev.craniumproperty.util.LibraryUtil;
import com.dev.craniumproperty.util.ModelValidateUtil;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelValidateUtil modelValidateUtil;

    @Autowired
    private LibraryUtil libraryUtil;

    @Override
    @Transactional
    public ResponseUserDTO createUser(SignupDTO signupDTO) {
        // Validasi email 
        modelValidateUtil.checkUserExist(signupDTO.getEmail());
        ResponseUserDTO responseUserDto = new ResponseUserDTO<>();
        // Tidak perlu name
        try {
            UserEntity user = new UserEntity();
            user.setEmail(signupDTO.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
            user.setCreatedAt(new Date());
            user.setCreatedBy(1);
            user.setUpdatedAt(new Date());
            user.setUpdatedBy(1);
            user.setDeleted(false);
            user.setDeletedAt(new Date());
            user.setDeletedBy(1);
            UserEntity createdUser = userRepository.save(user);
            UserDTO userDTO = new UserDTO();
            userDTO.setId(createdUser.getId());
            userDTO.setEmail(createdUser.getEmail());
            userDTO.setCreatedAt(createdUser.getCreatedAt());
            userDTO.setCreatedBy(createdUser.getCreatedBy());
            userDTO.setUpdatedAt(createdUser.getUpdatedAt());
            userDTO.setUpdatedBy(createdUser.getUpdatedBy());
            userDTO.setDeleted(createdUser.getDeleted());
            userDTO.setDeletedAt(createdUser.getDeletedAt());
            userDTO.setDeletedBy(createdUser.getDeletedBy());
            responseUserDto.setCode(ConstantUtil.STATUS_SUCCESS);
            responseUserDto.setInfo(ConstantUtil.MESSAGE_SUCCESS);
            responseUserDto.setData(userDTO);
            return responseUserDto;

        } catch (Exception e) {
            throw new ResultInternalServerErrorException("Internal server error");
        }

    }

    public ResponseUserDTO login(LoginDTO loginDTO) {
        UserEntity userExist = modelValidateUtil.checkUser(loginDTO.getEmail());
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        ResponseUserDTO responseUserDto = new ResponseUserDTO<>();
        if (!(passwordEncoder.matches(loginDTO.getPassword(), userExist.getPassword())))
            throw new ResultNotFoundException("Password is wrong");
        try {
            String token = libraryUtil.generateJwtToken(userExist);
            
            Map data = new HashMap();
            data.put("token", token);
            data.put("userEmail", userExist.getEmail());
            responseUserDto.setCode(ConstantUtil.STATUS_SUCCESS);
            responseUserDto.setInfo(ConstantUtil.MESSAGE_SUCCESS);
            responseUserDto.setData(data);
            return responseUserDto;
        } catch (Exception e) {
            log.error("[FATAL] " + e);
            throw new ResultInternalServerErrorException("Internal server error");
        }
    }
}
