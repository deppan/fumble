package com.tsinsi.account.application.port.in;

import com.tsinsi.account.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAccounts(long beforeId, long afterId);

    Account findOne(String username);
}
