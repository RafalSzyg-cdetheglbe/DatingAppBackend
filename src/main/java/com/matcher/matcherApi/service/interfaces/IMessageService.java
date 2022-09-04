package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.model.Message;

import java.security.Principal;
import java.util.List;

public interface IMessageService {
    void addMessage(Long userId, Long matchId,String content);

    List<Message> getMatchMessages(Long matchId);
}
