package com.tsinsi.auth.application.port.out;

import com.tsinsi.auth.entity.Account;

import java.util.List;

public interface AccountProvider {

    List<Account> findBefore(long id);

    List<Account> findAfter(long id);

    Account findByUsername(String username);
}
