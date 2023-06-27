package com.dev.craniumproperty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.dev.craniumproperty.dto.response.ResponseDTO;
import com.dev.craniumproperty.util.ConstantUtil;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResultNotFound(ResultNotFoundException ex) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ConstantUtil.STATUS_NOT_FOUND);
        responseDTO.setInfo(ex.getMessage());
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
    }

    // @ExceptionHandler(ResultExistException.class)
    // public ResponseEntity<CustomErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
    //     CustomErrorResponse errors = new CustomErrorResponse();
    //     errors.setTimestamp(LocalDateTime.now());
    //     errors.setError(ex.getMessage());
    //     errors.setStatus(HttpStatus.NOT_FOUND.value());
    //     return new ResponseEntity<>(errors, HttpStatus.NOT_FOUND);
    // }

    @ExceptionHandler(ResultExistException.class)
    public ResponseEntity<Object> handleResultBadRequestException(ResultExistException ex){
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setCode(ConstantUtil.STATUS_EXIST);
        responseDTO.setInfo(ex.getMessage());
        responseDTO.setData(null);

        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}

