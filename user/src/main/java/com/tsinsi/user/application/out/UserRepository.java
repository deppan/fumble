package com.tsinsi.user.application.out;

import com.tsinsi.user.domain.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findByUsername(String username);

    Optional<List<User>> findBefore(@Param("id") long id);

    Optional<List<User>> findAfter(@Param("id") long id);

}
