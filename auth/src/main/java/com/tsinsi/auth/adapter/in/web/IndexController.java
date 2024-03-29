package com.tsinsi.auth.adapter.in.web;

import com.tsinsi.auth.adapter.in.web.param.SignIn;
import com.tsinsi.auth.adapter.in.web.param.Signup;
import com.tsinsi.auth.application.port.in.AccountService;
import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import com.tsinsi.auth.entity.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j(topic = "account")
@RestController
public class IndexController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/signup")
    public ResponseEntity<Object> signup(@Validated Signup signup) {
        Account tmp = AccountMapper.mapperBy(signup);
        Account account = accountService.signup(tmp);
        String token = generateToken(String.valueOf(account.getId()));
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated SignIn signIn) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authentication);

        String name = authenticate.getName();
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(name);
        claimSet.setUid(Long.parseLong(name));

        String token = jwtHelper.generate(claimSet);
        return ResponseEntity.ok(Map.of("token", token));
    }

    private String generateToken(String uid) {
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(uid);
        claimSet.setUid(Long.parseLong(uid));
        return jwtHelper.generate(claimSet);
    }
}
