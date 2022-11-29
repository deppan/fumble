package com.tsinsi.account.adapter.out.mapper;

import com.tsinsi.account.adapter.out.mapper.reader.AccountReaderMapper;
import com.tsinsi.account.adapter.out.mapper.writer.AccountWriterMapper;
import com.tsinsi.account.application.port.out.AccountRepository;
import com.tsinsi.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    private final AccountWriterMapper accountWriterMapper;
    private final AccountReaderMapper accountReaderMapper;

    @Autowired
    public AccountRepositoryImpl(AccountWriterMapper accountWriterMapper, AccountReaderMapper accountReaderMapper) {
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
    @Caching(
            cacheable = {@Cacheable(value = "user", key = "#username", unless = "#result == null")},
            put = {@CachePut(value = "user", key = "#result.id", unless = "#result == null")}
    )
    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
