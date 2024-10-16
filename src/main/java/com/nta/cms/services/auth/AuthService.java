package com.nta.cms.services.auth;

import com.nta.cms.data.http.req.auth.AuthenticationRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthService {

    /**
     * Generate token or Google Authenticator code
     * @param request request
     * @param httpServletRequest client request
     * @return Token or Google Authenticator code
     */
    String authenticate(AuthenticationRequest request, HttpServletRequest httpServletRequest);

}