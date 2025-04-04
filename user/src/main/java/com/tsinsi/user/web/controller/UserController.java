package com.tsinsi.user.web.controller;

import com.tsinsi.user.application.in.UserUseCase;
import com.tsinsi.user.domain.persistence.sql.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sqids.Sqids;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserUseCase userUseCase;

    private final Sqids sqids;

    public UserController(UserUseCase userUseCase, Sqids sqids) {
        this.userUseCase = userUseCase;
        this.sqids = sqids;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> users(@RequestParam(value = "before", required = false) String before,
                                        @RequestParam(value = "after", required = false) String after) {
        List<UserEntity> userEntities;
        long id = 0;
        try {
            id = sqids.decode(before).getFirst();
        } catch (Exception ignored) {
        }
        if (id > 0 && !Strings.isEmpty(before)) {
            userEntities = userUseCase.findBeforeUsers(id);
        } else if (id > 0 && !Strings.isEmpty(after)) {
            userEntities = userUseCase.findAfterUsers(id);
        } else {
            userEntities = userUseCase.findAfterUsers(0);
        }

        return ResponseEntity.ok(userEntities);
    }

    @GetMapping(value = {"/user/{username}"})
    public ResponseEntity<Object> user(@PathVariable("username") String username) {
        UserEntity userEntity = userUseCase.findOne(username);
        return ResponseEntity.ok(userEntity);
    }

}
