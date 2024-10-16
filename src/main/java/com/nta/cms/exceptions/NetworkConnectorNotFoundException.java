package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class NetworkConnectorNotFoundException extends BusinessException {
    public NetworkConnectorNotFoundException() {
        this.status = HttpStatus.BAD_REQUEST;
        this.message = "Network connector not found";
    }
}
