package com.tsinsi.user.application.out;

import com.tsinsi.user.domain.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

    Optional<User> findByUsername(String username);

    Optional<List<User>> findBefore(@Param("id") long id);

    Optional<List<User>> findAfter(@Param("id") long id);

}
