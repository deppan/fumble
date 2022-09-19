package com.tsinsi.auth.adapter.in.web;

import com.tsinsi.auth.adapter.in.web.param.Signup;
import com.tsinsi.auth.entity.Account;

public class AccountMapper {
    public static Account mapperBy(Signup signup) {
        Account account = new Account();
        account.setUsername(signup.getUsername());
        account.setNickname(signup.getNickname());
        account.setPassword(signup.getPassword());
        account.setGender(signup.getGender());
        return account;
    }
}
