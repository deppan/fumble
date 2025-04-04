package com.tsinsi.user.application.out;

import com.tsinsi.user.domain.persistence.sql.UserEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserPersistencePort {

    UserEntity findByUsername(String username);

    List<UserEntity> findBefore(@Param("id") long id);

    List<UserEntity> findAfter(@Param("id") long id);

}
