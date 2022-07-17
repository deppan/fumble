package com.tsinsi.fumble.account.application.port.in;

import com.tsinsi.fumble.account.entity.Account;

import java.util.List;

public interface FindAccount {

    List<Account> findAccounts(String before, String after);

    Account findOne(String username);
}
