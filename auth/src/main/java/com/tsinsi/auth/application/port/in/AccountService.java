package com.tsinsi.auth.application.port.in;

import com.tsinsi.auth.entity.Account;

public interface AccountService {

    Account signup(Account account);

    Account findOne(String username);

}
