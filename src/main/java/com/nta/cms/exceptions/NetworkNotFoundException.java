package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;


public class NetworkNotFoundException extends BusinessException {

    public NetworkNotFoundException(String network) {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = network + " not found";
    }
}
