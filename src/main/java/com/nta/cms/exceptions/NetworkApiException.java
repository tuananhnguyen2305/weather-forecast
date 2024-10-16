package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class NetworkApiException extends BusinessException {
    public NetworkApiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public NetworkApiException(HttpStatus status, String message, String detailMessage){
        this.status = status;
        this.message = message;
        this.detailMessage = detailMessage;
    }
}
