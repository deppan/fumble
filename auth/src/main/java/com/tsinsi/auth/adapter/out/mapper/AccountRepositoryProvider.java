package com.tsinsi.auth.adapter.out.mapper;

import com.tsinsi.auth.adapter.out.mapper.reader.AccountReaderMapper;
import com.tsinsi.auth.adapter.out.mapper.writer.AccountWriterMapper;
import com.tsinsi.auth.application.port.out.AccountProvider;
import com.tsinsi.auth.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

@Repository
@CacheConfig(cacheNames = "account")
public class AccountRepositoryProvider implements AccountProvider {

    private final AccountWriterMapper accountWriterMapper;
    private final AccountReaderMapper accountReaderMapper;

    @Autowired
    public AccountRepositoryProvider(AccountWriterMapper accountWriterMapper, AccountReaderMapper accountReaderMapper) {
        this.accountWriterMapper = accountWriterMapper;
        this.accountReaderMapper = accountReaderMapper;
    }

    @Override
    public Account signup(Account account) {
        return accountWriterMapper.insert(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
