package com.tsinsi.fumble.account.adapter.in.web;

import com.tsinsi.fumble.account.application.port.in.FindAccount;
import com.tsinsi.fumble.account.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j(topic = "account")
@RestController
public class IndexController {

    private final FindAccount findAccount;

    public IndexController(FindAccount findAccount) {
        this.findAccount = findAccount;
    }

    @GetMapping(value = {"/accounts"})
    public ResponseEntity<Object> accounts(@RequestParam(value = "before", required = false) String before,
                                           @RequestParam(value = "after", required = false) String after) {
        List<Account> accounts = findAccount.findAccounts(before, after);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping(value = {"/account/{username}"})
    public ResponseEntity<Object> account(@PathVariable("username") String username) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info(principal.toString());

        Account account = findAccount.findOne(username);
        return ResponseEntity.ok(account);
    }
}
