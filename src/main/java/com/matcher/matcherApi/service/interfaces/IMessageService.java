package com.matcher.matcherApi.service.interfaces;

import java.security.Principal;

public interface IMessageService {
    void addMessage(Long conversationId,String content, String email);
}
