package com.tsinsi.account.application.port.in;

import com.tsinsi.account.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAccounts(String before, String after);

    Account findOne(String username);
}
