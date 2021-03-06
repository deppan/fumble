package com.tsinsi.account.adapter.out.mapper.writer;

import com.tsinsi.account.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountWriterMapper {

    @Insert("insert into tb_accounts (username, password, gender) values(#{username}, #{password}, #{gender})")
    int insert(Account account);
}
