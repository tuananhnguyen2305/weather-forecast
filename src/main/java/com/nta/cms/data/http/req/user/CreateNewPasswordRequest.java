package com.nta.cms.data.http.req.user;

import lombok.Data;

@Data
public class CreateNewPasswordRequest {
    private String token;
    private String password;
}
