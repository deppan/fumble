package com.tsinsi.user.adapter.out.persistence.mysql.repository;

import com.tsinsi.user.adapter.out.persistence.mysql.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

    @Query("select new UserEntity(id, username, nickname, gender) from UserEntity where id <= :id")
    Optional<List<UserEntity>> findBefore(@Param("id") long id);

    @Query("select new UserEntity(id, username, nickname, gender) from UserEntity where id > :id")
    Optional<List<UserEntity>> findAfter(@Param("id") long id);

    Optional<UserEntity> findByUsername(String username);

}
