package com.tsinsi.fumble.account.mapper;

import com.tsinsi.fumble.account.entity.Account;
import com.tsinsi.fumble.account.mapper.reader.AccountReaderMapper;
import com.tsinsi.fumble.account.mapper.writer.AccountWriterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "account")
public class AccountRepository {

    private final AccountWriterMapper accountWriterMapper;
    private final AccountReaderMapper accountReaderMapper;

    @Autowired
    public AccountRepository(AccountWriterMapper accountWriterMapper, AccountReaderMapper accountReaderMapper) {
        this.accountWriterMapper = accountWriterMapper;
        this.accountReaderMapper = accountReaderMapper;
    }

    @Cacheable(key = "#p0")
    public Account findById(long id) {
        System.out.println("aaaaa");
        return accountReaderMapper.findById(id);
    }

    public Account findBy(long id) {
        return accountReaderMapper.findBy(id);
    }

    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
