package com.tsinsi.user.adapter.in;

import com.tsinsi.user.application.in.UserService;
import com.tsinsi.user.application.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sqids.Sqids;

import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserService userService;

    private final Sqids sqids;

    public UserController(UserService userService, Sqids sqids) {
        this.userService = userService;
        this.sqids = sqids;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<UserResponse>> users(@RequestParam(value = "before", required = false) String before,
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

        return ResponseEntity.ok(userService.findUsers(beforeId, afterId));
    }

    @GetMapping(value = {"/user/{username}"})
    public ResponseEntity<UserResponse> user(@PathVariable("username") String username) {
        return ResponseEntity.ok(userService.findOne(username));
    }

}
