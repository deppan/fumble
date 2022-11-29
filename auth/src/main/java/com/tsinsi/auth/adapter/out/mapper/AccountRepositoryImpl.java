package com.tsinsi.auth.adapter.out.mapper;

import com.tsinsi.auth.adapter.out.mapper.reader.AccountReaderMapper;
import com.tsinsi.auth.adapter.out.mapper.writer.AccountWriterMapper;
import com.tsinsi.auth.application.port.out.AccountRepository;
import com.tsinsi.auth.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Account signup(Account account) {
        return accountWriterMapper.insert(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountReaderMapper.findByUsername(username);
    }
}
