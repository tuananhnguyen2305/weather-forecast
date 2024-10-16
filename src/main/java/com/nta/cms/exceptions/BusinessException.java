package com.nta.cms.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {
    protected HttpStatus status;
    protected String message;
    protected String detailMessage;
    protected Object objectMessage;

    public BusinessException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
