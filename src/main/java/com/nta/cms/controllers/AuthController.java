package com.nta.cms.controllers;


import com.nta.cms.data.http.req.auth.AuthenticationRequest;
import com.nta.cms.data.http.resp.ApiResponse;
import com.nta.cms.services.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<ApiResponse> authenticate(HttpServletRequest httpServletRequest, @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(new ApiResponse(200, "Authenticate success", authenticationService.authenticate(request, httpServletRequest)));
    }


}
