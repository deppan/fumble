package com.tsinsi.user.adapter.in;

import com.tsinsi.user.application.in.UserUseCase;
import com.tsinsi.user.domain.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sqids.Sqids;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<User>> users(@RequestParam(value = "before", required = false) String before,
                                            @RequestParam(value = "after", required = false) String after) {
        long beforeId = 0;
        long afterId = 0;
        if (StringUtils.hasLength(before)) {
            try {
                beforeId = sqids.decode(before).getFirst();
            } catch (Exception ignored) {
            }
        }
        if (StringUtils.hasLength(after)) {
            try {
                afterId = sqids.decode(after).getFirst();
            } catch (Exception ignored) {
            }
        }

        return userUseCase.findUsers(beforeId, afterId).map(ResponseEntity::ok).orElse(ResponseEntity.ok(List.of()));
    }

    @GetMapping(value = {"/user/{username}"})
    public ResponseEntity<User> user(@PathVariable("username") String username) {
        return userUseCase.findOne(username).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

}
