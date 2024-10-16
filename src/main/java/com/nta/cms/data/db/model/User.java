package com.nta.cms.data.db.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private String phone;
    private String role; // default role is USER
    private int failedAttempt = 0;
    private String resetPasswordToken;
    private long resetPasswordTokenExpire = 0;
}
