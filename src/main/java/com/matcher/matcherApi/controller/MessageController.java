package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.model.Message;
import com.matcher.matcherApi.service.interfaces.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins="http://localhost:4200")
public class MessageController {
    private final IMessageService iMessageService;

    public MessageController(IMessageService iMessageService) {
        this.iMessageService = iMessageService;
    }

    @PostMapping("message/{matchId}/{userId}")
    public void sendMessage(@PathVariable Long matchId,@PathVariable Long userId, @RequestBody String content) {
        this.iMessageService.addMessage(userId, matchId, content);
    }
        @GetMapping("message/{matchId}")
                public List<Message> getMatchMessages(@PathVariable Long matchId){
           return this.iMessageService.getMatchMessages(matchId);
        }
    }

