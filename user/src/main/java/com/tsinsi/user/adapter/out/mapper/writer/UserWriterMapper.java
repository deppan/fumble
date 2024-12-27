package com.tsinsi.user.adapter.out.mapper.writer;

import com.tsinsi.user.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserWriterMapper {

    @Insert("insert into users(username, password, nickname, gender) values(#{user.username}, #{user.password}, #{user.nickname}, #{user.gender})")
    int insert(User user);
}
