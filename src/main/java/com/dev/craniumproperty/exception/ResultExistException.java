package com.dev.craniumproperty.exception;

public class ResultExistException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public ResultExistException(String message) {
        super(message);
    }
}
