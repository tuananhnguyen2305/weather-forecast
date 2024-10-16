package com.nta.cms.services.auth.impl;


import com.nta.cms.data.db.model.User;
import com.nta.cms.data.http.req.auth.AuthenticationRequest;
import com.nta.cms.exceptions.BusinessException;
import com.nta.cms.exceptions.UserNotFoundException;
import com.nta.cms.services.auth.AuthService;
import com.nta.cms.services.user.UserService;
import com.nta.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    static final BusinessException lockedUserException = BusinessException.builder()
            .message("The account is locked, please contact your admin.")
            .status(HttpStatus.BAD_REQUEST)
            .build();

    static final BusinessException wrongPasswordException = BusinessException.builder()
            .message("Wrong email or password")
            .status(HttpStatus.BAD_REQUEST)
            .build();

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final MongoTemplate mongoTemplate;

    @Override
    public String authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest) {


        final int FAILURE_LIMIT = 15;
        User user = userService.getUserByUsername(request.getUsername());
        if (user == null) {
            throw new UserNotFoundException(request.getUsername());
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException e) {
            user.setFailedAttempt(user.getFailedAttempt() + 1);
            if (user.getFailedAttempt() > FAILURE_LIMIT) {
                user.setFailedAttempt(0);
            }
            mongoTemplate.save(user);
            throw wrongPasswordException;
        }
        return JwtUtils.generateToken(user.getUsername());
    }
}