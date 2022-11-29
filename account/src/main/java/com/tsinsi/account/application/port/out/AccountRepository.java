package com.tsinsi.account.application.port.out;

import com.tsinsi.account.entity.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> findBefore(long id);

    List<Account> findAfter(long id);

    Account findByUsername(String username);
}
