package com.tsinsi.auth.application.port.in;

import com.tsinsi.auth.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> findAccounts(String before, String after);

    Account findOne(String username);
}
