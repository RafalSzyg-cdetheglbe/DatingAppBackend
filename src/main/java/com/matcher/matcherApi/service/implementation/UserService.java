package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.DTO.UserDTO;
import com.matcher.matcherApi.model.*;
import com.matcher.matcherApi.repository.MatchMemberRepository;
import com.matcher.matcherApi.repository.MatchRepository;
import com.matcher.matcherApi.repository.UserRepository;
import com.matcher.matcherApi.service.interfaces.IUserService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private final MatchMemberRepository matchMemberRepository;

    public UserService(UserRepository userRepository, MatchRepository matchRepository, MatchMemberRepository matchMemberRepository) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.matchMemberRepository = matchMemberRepository;
    }


    @Override
    public List<User> getUsers() {
        List<User> users = this.userRepository.findAll();
        System.out.println(users);
        return users;
    }

    @Override
    public void addUser(UserDTO userDTO) {
        User user = new User();
        UserRole userRole = new UserRole();
        GenderInterest genderInterest = new GenderInterest();
        genderInterest.setId(userDTO.getGenderInterest());
        userRole.setId(1L);
        user.setUserRole(userRole);
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setGender(userDTO.getGender());
        user.setGenderInterest(genderInterest);
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setDeleted(false);
        System.out.println(user);
        this.userRepository.save(user);
    }

    @Override
    public List<User> getUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalStateException("NotFoundInDatabase"));
        List<User> users = new ArrayList<>();
        users.add(user);
        return users;
    }

    @Override
    public List<User> getUsersToMatch(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new IllegalStateException("NotFoundInDatabase"));
        // List<Match> matches=this.matchRepository.findByUserId(id);

        List<User> users = this.userRepository.findAll();
        users.removeIf(u -> u.getId().equals(id));




        return users;
    }
}

