package com.tsinsi.account.application.port.in;

import com.tsinsi.account.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAfterAccounts(long afterId);

    List<Account> findBeforeAccounts(long beforeId);

    Account findOne(String username);
}
