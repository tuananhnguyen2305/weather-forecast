package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class NetworkConnectorInvalidException extends BusinessException {
    public NetworkConnectorInvalidException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Network connector is invalid";
    }

    public NetworkConnectorInvalidException(String message) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = message;
    }
}
