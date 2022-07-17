package com.tsinsi.fumble.account.adapter.out.mapper.reader;

import com.tsinsi.fumble.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountReaderMapper {

    @Select("select id, username, password, nickname, gender from tb_accounts where id < #{id} limit 10")
    List<Account> findBefore(long id);

    @Select("select id, username, password, nickname, gender from tb_accounts where id > #{id} limit 10")
    List<Account> findAfter(long id);

    @Select("select id, username, password, nickname, gender from tb_accounts where username = #{username} limit 1")
    Account findByUsername(String username);
}
