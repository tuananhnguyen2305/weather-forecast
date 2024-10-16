package com.nta.cms.services.user;

import com.nta.cms.data.db.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserByUsername(String username);

    List<User> findAll();
}
