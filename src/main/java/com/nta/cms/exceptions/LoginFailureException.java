package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class LoginFailureException extends BusinessException {
    public LoginFailureException() {
        this.message = "Login failure";
        this.status = HttpStatus.BAD_REQUEST;
    }
}
