package com.tsinsi.user.domain.persistence.sql;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("select new UserEntity(id, username, nickname, gender) from UserEntity where id <= :id")
    List<UserEntity> findBefore(@Param("id") long id);

    @Query("select new UserEntity(id, username, nickname, gender) from UserEntity where id > :id")
    List<UserEntity> findAfter(@Param("id") long id);

    @Cacheable(value = "user", key = "#username", unless = "#result == null")
    Optional<UserEntity> findByUsername(String username);

}
