package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.service.implementation.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public void addUser(User user){
       // this.userService.addUser(user);
    }

}
