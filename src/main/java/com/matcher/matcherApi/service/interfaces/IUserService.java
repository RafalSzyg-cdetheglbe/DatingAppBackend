package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.DTO.UserDTO;
import com.matcher.matcherApi.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


public interface IUserService {
    UserDetails loadUserByUsername(String username);
}
