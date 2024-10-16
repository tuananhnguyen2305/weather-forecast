package com.nta.cms.data.db.repo;

import com.nta.cms.data.db.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    User findUserByEmail(String email);

    User findUserById(String id);
}
