package com.tsinsi.account.adapter.in.web;

import com.tsinsi.account.application.port.in.AccountService;
import com.tsinsi.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.hashids.Hashids;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j(topic = "user")
@RestController
public class IndexController {

    private final AccountService accountService;

    private final Hashids hashids;

    public IndexController(AccountService findAccount, Hashids hashids) {
        this.accountService = findAccount;
        this.hashids = hashids;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> accounts(@RequestParam(value = "before", required = false) String before,
                                           @RequestParam(value = "after", required = false) String after) {
        List<Account> accounts;
        long id = 0;
        try {
            id = hashids.decode(before)[0];
        } catch (Exception ignored) {
        }
        if (id > 0 && !Strings.isEmpty(before)) {
            accounts = accountService.findBeforeAccounts(id);
        } else if (id > 0 && !Strings.isEmpty(after)) {
            accounts = accountService.findAfterAccounts(id);
        } else {
            accounts = accountService.findAfterAccounts(0);
        }

        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value = {"/user/{username}"})
    public ResponseEntity<Object> account(@PathVariable("username") String username) {
        Account account = accountService.findOne(username);
        return ResponseEntity.ok(account);
    }
}
