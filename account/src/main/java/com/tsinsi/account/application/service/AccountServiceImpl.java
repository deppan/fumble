package com.tsinsi.account.application.service;

import com.tsinsi.account.application.port.in.AccountService;
import com.tsinsi.account.application.port.out.AccountRepository;
import com.tsinsi.account.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Account> findAfterAccounts(long afterId) {
        return repository.findAfter(afterId);
    }

    @Override
    public List<Account> findBeforeAccounts(long afterId) {
        return repository.findBefore(afterId);
    }

    @Override
    public Account findOne(String username) {
        return repository.findByUsername(username);
    }
}
