package com.tsinsi.user.adapter.out.mapper.reader;

import com.tsinsi.user.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserReaderMapper {

    @Select("select id, username, password, nickname, gender from tb_users where id < #{id} limit 10")
    List<User> findBefore(long id);

    @Select("select id, username, password, nickname, gender from tb_users where id > #{id} limit 10")
    List<User> findAfter(long id);

    @Select("select id, username, password, nickname, gender from tb_users where username = #{username} limit 1")
    User findByUsername(String username);
}
