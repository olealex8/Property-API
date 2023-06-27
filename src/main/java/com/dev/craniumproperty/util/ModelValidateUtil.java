package com.dev.craniumproperty.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dev.craniumproperty.entity.UserEntity;
import com.dev.craniumproperty.exception.ResultExistException;
import com.dev.craniumproperty.exception.ResultNotFoundException;
import com.dev.craniumproperty.repository.UserRepository;

@Component
public class ModelValidateUtil {
    @Autowired
    private UserRepository userRepository;

    public UserEntity checkUserExist(String email) {
        UserEntity userExist = userRepository.findOneByEmailAndDeleted(email, false);

        if(userExist != null) throw new ResultExistException("Email already registered");

        return userExist;
    }

    public UserEntity checkUser(String email) {
        UserEntity userExist = userRepository.findOneByEmailAndDeleted(email, false);

        if(userExist == null) throw new ResultNotFoundException("Email or user not found");

        return userExist;
    }

    
}
