package com.tsinsi.fumble.account.mapper.reader;

import com.tsinsi.fumble.account.entity.Account;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AccountReaderMapper {

    Account findById(Long id);

    @Results(id = "accountMap", value = {
            @Result(property = "id.value", column = "id")
    })
    @Select("select id, username, password, gender from tb_accounts where id = #{id} limit 1")
    Account findBy(Long id);

    @ResultMap(value = {"accountMap"})
    @Select("select id, username, password, gender from tb_accounts where username = #{username} limit 1")
    Account findByUsername(String username);
}
