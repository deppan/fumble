package com.tsinsi.auth.adapter.in.web;

import com.tsinsi.auth.configuration.JwtHelper;
import com.tsinsi.auth.configuration.util.ClaimSet;
import lombok.extern.slf4j.Slf4j;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j(topic = "account")
@RestController
public class IndexController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @PostMapping(value = "/login")
    public ResponseEntity<Object> accounts(@RequestParam(name = "username") String username,
                                           @RequestParam(name = "password") String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authentication);

        String name = authenticate.getName();
        ClaimSet claimSet = new ClaimSet();
        claimSet.setDays(3);
        claimSet.setUsername(name);
        claimSet.setUid(Long.parseLong(name));

        String token = jwtHelper.generate(claimSet);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
