package com.tsinsi.auth.application.service;

import com.tsinsi.auth.adapter.out.mapper.AccountRepositoryImpl;
import com.tsinsi.auth.application.port.in.AccountService;
import com.tsinsi.auth.entity.Account;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepositoryImpl repository;

    @Autowired
    public AccountServiceImpl(AccountRepositoryImpl repository) {
        this.repository = repository;
    }

    @Override
    public Account signup(Account account) {
        if (Strings.isEmpty(account.getUsername()) || Strings.isEmpty(account.getPassword())) {
            throw new RuntimeException();
        }
        return repository.signup(account);
    }

    @Override
    public Account findOne(String username) {
        return repository.findByUsername(username);
    }
}
