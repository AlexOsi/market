package com.osintsev.market.rest.controller;

import com.osintsev.market.database.user.UserService;
import com.osintsev.market.exception.UserExistsException;
import com.osintsev.market.rest.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping(path = "user/create", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody @Valid User user) {
        try {
            userService.createUser(user);
        } catch (UserExistsException e) {
            e.printStackTrace();
        }
    }
}
