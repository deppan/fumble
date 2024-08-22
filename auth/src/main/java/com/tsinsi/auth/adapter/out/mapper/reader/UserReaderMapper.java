package com.tsinsi.auth.adapter.out.mapper.reader;

import com.tsinsi.auth.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserReaderMapper {

    @Select("select id, username, password, nickname, gender from tb_users where username = #{username} limit 1")
    User findByUsername(String username);
}
