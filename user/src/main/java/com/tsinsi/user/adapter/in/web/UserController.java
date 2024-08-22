package com.tsinsi.user.adapter.in.web;

import com.tsinsi.user.adapter.out.mapper.reader.InventoryReaderMapper;
import com.tsinsi.user.application.port.in.UserService;
import com.tsinsi.user.entity.Inventory;
import com.tsinsi.user.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.sqids.Sqids;

import java.util.List;

@Slf4j(topic = "user")
@RestController
public class UserController {

    private final UserService userService;

    private final Sqids sqids;

    public UserController(UserService userService, Sqids sqids) {
        this.userService = userService;
        this.sqids = sqids;
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Object> users(@RequestParam(value = "before", required = false) String before,
                                        @RequestParam(value = "after", required = false) String after) {
        List<User> users;
        long id = 0;
        try {
            id = sqids.decode(before).get(0);
        } catch (Exception ignored) {
        }
        if (id > 0 && !Strings.isEmpty(before)) {
            users = userService.findBeforeAccounts(id);
        } else if (id > 0 && !Strings.isEmpty(after)) {
            users = userService.findAfterAccounts(id);
        } else {
            users = userService.findAfterAccounts(0);
        }

        return ResponseEntity.ok(users);
    }

    @Autowired
    InventoryReaderMapper inventoryReaderMapper;

    @GetMapping(value = {"/user/{username}"})
    public ResponseEntity<Object> user(@PathVariable("username") String username) {
        List<Inventory> inventories = inventoryReaderMapper.find(1);

        User user = userService.findOne(username);
        return ResponseEntity.ok(user);
    }
}
