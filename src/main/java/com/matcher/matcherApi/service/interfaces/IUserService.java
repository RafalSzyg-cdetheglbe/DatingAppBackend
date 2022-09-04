package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.DTO.UserDTO;
import com.matcher.matcherApi.model.User;

import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService {
public List<User> getUsers();


    void addUser(UserDTO userDTO);

    List<User> getUser(Long id);

    List<User> getUsersToMatch(Long id);
}
