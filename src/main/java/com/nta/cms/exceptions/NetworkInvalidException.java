package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class NetworkInvalidException extends BusinessException {
    public NetworkInvalidException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Network information is invalid";
    }

    public NetworkInvalidException(String message) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = message;
    }
}
