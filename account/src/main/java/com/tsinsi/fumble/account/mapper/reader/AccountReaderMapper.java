package com.tsinsi.fumble.account.mapper.reader;

import com.tsinsi.fumble.account.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AccountReaderMapper {

    @Select("select id, username, password, gender from tb_accounts where id = #{id} limit 1")
    Account findBy(Long id);

    @Select("select id, username, password, gender from tb_accounts where id > #{id} limit 10")
    List<Account> findAccounts(long id);

    Account findById(Long id);

    @Select("select id, username, password, gender from tb_accounts where username = #{username} limit 1")
    Account findByUsername(String username);
}
