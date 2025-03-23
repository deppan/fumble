package com.tsinsi.user.application.port.out.repository;

import com.tsinsi.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select id, username, password, nickname, gender from users where id < :id", nativeQuery = true)
    List<User> findBefore(@Param("id") long id);

    @Query(value = "select id, username, nickname, gender from users where id > :id", nativeQuery = true)
    List<User> findAfter(@Param("id") long id);

    User findByUsername(String username);
}
