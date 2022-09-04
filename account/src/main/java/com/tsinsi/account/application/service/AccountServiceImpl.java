package com.tsinsi.account.application.service;

import com.tsinsi.account.adapter.out.mapper.AccountRepositoryProvider;
import com.tsinsi.account.application.port.in.AccountService;
import com.tsinsi.account.entity.Account;
import org.apache.logging.log4j.util.Strings;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepositoryProvider repository;
    private final Hashids hashids;

    @Autowired
    public AccountServiceImpl(AccountRepositoryProvider repository, Hashids hashids) {
        this.repository = repository;
        this.hashids = hashids;
    }

    @Override
    public List<Account> findAccounts(String before, String after) {
        boolean isAfter = true;
        String hash = null;
        long id = 0;
        if (!Strings.isEmpty(before)) {
            isAfter = false;
            hash = before;
        } else if (!Strings.isEmpty(after)) {
            hash = after;
        }

        if (!Strings.isEmpty(hash)) {
            try {
                id = hashids.decode(hash)[0];
            } catch (Exception ignored) {
            }
        }

        List<Account> accounts;
        if (isAfter) {
            accounts = repository.findAfter(id);
        } else {
            accounts = repository.findBefore(id);
        }
        return accounts;
    }

    @Override
    public Account findOne(String username) {
        return repository.findByUsername(username);
    }
}
