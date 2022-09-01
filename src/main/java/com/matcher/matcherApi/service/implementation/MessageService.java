package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Conversation;
import com.matcher.matcherApi.model.Message;
import com.matcher.matcherApi.repository.ConversationRepository;
import com.matcher.matcherApi.repository.MessageRepository;
import com.matcher.matcherApi.service.interfaces.IMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService implements IMessageService {
    private final ConversationRepository conversationRepository;
    private final MessageRepository messageRepository;

    public MessageService(ConversationRepository conversationRepository, MessageRepository messageRepository) {
        this.conversationRepository = conversationRepository;
        this.messageRepository = messageRepository;
    }

    public void addMessage(Long conversationId,String content,String email){
        LocalDateTime localDateTime=LocalDateTime.now();
        Message message=new Message();
        Conversation conversation=this.conversationRepository.findById(conversationId).orElseThrow(IllegalAccessError::new);
        message.setConversation(conversation);
        message.setSendDate(localDateTime);
        message.setDeleted(false);
        message.setContent(content);
        this.messageRepository.save(message);
    }
}
