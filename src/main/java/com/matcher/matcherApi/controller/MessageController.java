package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.service.interfaces.IMessageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/api")
public class MessageController {
    private final IMessageService iMessageService;

    public MessageController(IMessageService iMessageService) {
        this.iMessageService = iMessageService;
    }

    @PostMapping("/message/{conversationId}")
    public void sendMessage(@PathVariable Long conversationId, @RequestBody String content){

    }
}
