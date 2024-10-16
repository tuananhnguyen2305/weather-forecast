package com.nta.cms.exceptions;

import org.springframework.http.HttpStatus;

public class ToManyRequestException extends BusinessException {
    public ToManyRequestException(HttpStatus status, String message, String detailMessage){
        this.status = status;
        this.message = message;
        this.detailMessage = detailMessage;
    }
}
