package com.tsinsi.account.application.service;

import com.tsinsi.account.adapter.out.mapper.AccountRepositoryProvider;
import com.tsinsi.account.application.port.in.AccountService;
import com.tsinsi.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepositoryProvider repository;

    @Autowired
    public AccountServiceImpl(AccountRepositoryProvider repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> findAccounts(long beforeId, long afterId) {
        List<Account> accounts;
        if (beforeId != 0) {
            accounts = repository.findAfter(beforeId);
        } else {
            accounts = repository.findBefore(afterId);
        }
        return accounts;
    }

    @Override
    public Account findOne(String username) {
        return repository.findByUsername(username);
    }
}
