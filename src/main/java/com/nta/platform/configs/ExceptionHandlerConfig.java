package com.nta.platform.configs;


import com.nta.cms.data.http.resp.ApiResponse;
import com.nta.cms.exceptions.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse> apiException(BusinessException e) {
        int code = e.getStatus() != null ? e.getStatus().value() : 11111;
        if(e.getMessage() != null){
            return ResponseEntity.ok(new ApiResponse(code, e.getMessage(), e.getMessage()));
        }
        return ResponseEntity.ok(new ApiResponse(code, e.getMessage()));
    }

}
