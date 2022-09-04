package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.DTO.MatchDTO;
import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.service.interfaces.IMatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class MatchController {
    private final IMatchService iMatchService;

    public MatchController(IMatchService iMatchService) {
        this.iMatchService = iMatchService;
    }

    @PostMapping("match/{authorId}")
    public MatchDTO addMatch(@PathVariable("authorId") Long authorId, @RequestBody MatchDTO matchDTO){
        Long userid=matchDTO.getUser();

       return this.iMatchService.addMatch(userid,authorId);

    }

    @GetMapping("match/{id}")
    public List<Match> getUserMatches(@PathVariable("id")Long id){

        return this.iMatchService.getUserMatches(id);
    }

    @GetMapping("matches")
    public List<Match> getMatches(){

        return this.iMatchService.getMatches();
    }

}
