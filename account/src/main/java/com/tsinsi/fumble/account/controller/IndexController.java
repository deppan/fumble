package com.tsinsi.fumble.account.controller;

import com.tsinsi.fumble.account.entity.Account;
import com.tsinsi.fumble.account.mapper.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "account")
@RestController
public class IndexController {

    private final AccountRepository repository;

    public IndexController(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping(value = {"/", "/index"})
    public ResponseEntity<Object> index(@RequestParam(value = "index", required = false) String index) {
        Account account = repository.findById(1L);
        return ResponseEntity.ok(account);
    }

    @GetMapping(value = {"/account"})
    public ResponseEntity<Object> account() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info(principal.toString());

        Account account = repository.findById(1L);
        log.info(account.getUsername());

        return ResponseEntity.ok("index");
    }
}
