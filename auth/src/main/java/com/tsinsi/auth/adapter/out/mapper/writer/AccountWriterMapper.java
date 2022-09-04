package com.tsinsi.auth.adapter.out.mapper.writer;

import com.tsinsi.auth.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountWriterMapper {

    @Insert("insert into tb_accounts (username, password, gender) values(#{username}, #{password}, #{gender})")
    int insert(Account account);
}
