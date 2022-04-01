package com.tsinsi.fumble.controller;

import com.tsinsi.fumble.entity.User;
import com.tsinsi.fumble.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class WelcomeController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping(value = {"/", "/index"})
    public Object index(@RequestParam(value = "index") String index) {
        User user = userMapper.findById(1L);
        log.info(user.getId().getValue() + " " + user.getName());

        user = userMapper.findBy(1L);
        log.info(user.getId().getValue() + " " + user.getName());
        return Map.of("name", "springboot");
    }

    @GetMapping("/home")
    public Object home() {
        return Map.of("name", "home");
    }

    @GetMapping("/user")
    public Object user() {
        return Map.of("name", "user");
    }
}
