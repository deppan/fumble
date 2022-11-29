package com.tsinsi.auth.application.port.out;

import com.tsinsi.auth.entity.Account;

public interface AccountRepository {

    Account signup(Account account);

    Account findByUsername(String username);
}
