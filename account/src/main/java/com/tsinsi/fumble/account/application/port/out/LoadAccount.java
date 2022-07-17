package com.tsinsi.fumble.account.application.port.out;

import com.tsinsi.fumble.account.entity.Account;

import java.util.List;

public interface LoadAccount {

    List<Account> findBefore(long id);

    List<Account> findAfter(long id);

    Account findByUsername(String username);
}
