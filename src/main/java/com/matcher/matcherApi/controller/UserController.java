package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.DTO.UserDTO;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.service.implementation.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserService IuserService;

    public UserController( UserService iuserService) {
        IuserService = iuserService;

    }

    @GetMapping("/users")
    public List<User> getUsers(){
       return this.IuserService.getUsers();
    }

    @GetMapping("/users/{id}")
    public List<User> getUsers(@PathVariable("id")Long id){
        return this.IuserService.getUser(id);
    }

    @GetMapping("/users/matching/{id}")
        public List<User> getUsersToMatch(@PathVariable("id")Long id){
        return this.IuserService.getUsersToMatch(id);
    }



    @PostMapping("/users")
    public void addUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        this.IuserService.addUser(userDTO);
    }



}
