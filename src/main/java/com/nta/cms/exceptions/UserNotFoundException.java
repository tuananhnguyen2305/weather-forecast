package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        this.message = "User not found";
        this.status = HttpStatus.BAD_REQUEST;
    }

    public UserNotFoundException(String username) {
        this.message = "User not found with username: " + username;
        this.status = HttpStatus.BAD_REQUEST;
    }
}
