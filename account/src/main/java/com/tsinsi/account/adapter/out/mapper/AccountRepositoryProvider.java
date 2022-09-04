package com.tsinsi.account.adapter.out.mapper;

import com.tsinsi.account.adapter.out.mapper.reader.AccountReaderMapper;
import com.tsinsi.account.adapter.out.mapper.writer.AccountWriterMapper;
import com.tsinsi.account.application.port.out.AccountProvider;
import com.tsinsi.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<Account> findBefore(long id) {
        return accountReaderMapper.findBefore(id);
    }

    @Override
    public List<Account> findAfter(long id) {
        return accountReaderMapper.findAfter(id);
    }


    @Override
    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
