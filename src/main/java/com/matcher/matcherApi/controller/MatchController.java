package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.service.interfaces.IMatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api")
public class MatchController {
    private final IMatchService iMatchService;

    public MatchController(IMatchService iMatchService) {
        this.iMatchService = iMatchService;
    }

    @PostMapping("/match/{userid}")
    public void addMatch(@PathVariable Long userid, Principal principal){

        this.iMatchService.addMatch(userid,principal.getName());

    }
}
