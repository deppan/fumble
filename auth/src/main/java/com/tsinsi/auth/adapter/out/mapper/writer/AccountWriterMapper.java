package com.tsinsi.auth.adapter.out.mapper.writer;

import com.tsinsi.auth.entity.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface AccountWriterMapper {

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert into tb_accounts (username, password, nickname, gender) values(#{username}, #{password}, #{nickname}, #{gender})")
    Account insert(Account account);
}
