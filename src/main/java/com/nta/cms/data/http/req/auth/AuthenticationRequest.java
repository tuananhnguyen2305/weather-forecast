package com.nta.cms.data.http.req.auth;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String organizationCode;
    private String password;
    private String googleAuthenticatorCode;
}
