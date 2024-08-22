package com.tsinsi.auth.adapter.out.mapper.writer;

import com.tsinsi.auth.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface UserWriterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into tb_users (username, password, nickname, gender) values(#{username}, #{password}, #{nickname}, #{gender})")
    User insert(User user);
}
