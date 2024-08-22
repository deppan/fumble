package com.tsinsi.user.adapter.out.mapper.writer;

import com.tsinsi.user.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserWriterMapper {

    @Insert("insert into tb_accounts (username, password, gender) values(#{username}, #{password}, #{gender})")
    int insert(User account);
}
