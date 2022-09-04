package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.model.MatchMember;
import com.matcher.matcherApi.service.interfaces.IMatchMemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class MatchMemberController {
    private final IMatchMemberService iMatchMemberService;

    public MatchMemberController(IMatchMemberService iMatchMemberService) {
        this.iMatchMemberService = iMatchMemberService;
    }

    @GetMapping("matchmember/{id}")
    public List<MatchMember> getUserMembers(@PathVariable("id")Long id){
        return this.iMatchMemberService.getUserMembers(id);
    }
}
