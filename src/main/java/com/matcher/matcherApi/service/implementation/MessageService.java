package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.Message;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.repository.MatchRepository;
import com.matcher.matcherApi.repository.MessageRepository;
import com.matcher.matcherApi.repository.UserRepository;
import com.matcher.matcherApi.service.interfaces.IMessageService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
private final UserRepository userRepository;
private final MatchRepository matchRepository;
    public MessageService(MessageRepository messageRepository, UserRepository userRepository, MatchRepository matchRepository) {

        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
    }

    public void addMessage(Long userId,Long matchId,String content){
        LocalDateTime localDateTime=LocalDateTime.now();
    Message message = new Message();
        Match match=this.matchRepository.findById(matchId).orElseThrow(()->new IllegalStateException("Not found in database"));

        User user=this.userRepository.findById(userId).orElseThrow(()->new IllegalStateException("Not found in database"));

    message.setContent(content);
    message.setDeleted(false);
    message.setMatch(match);
    message.setSendDate(localDateTime);
    message.setUser(user);

    this.messageRepository.save(message);
    }

    @Override
    public List<Message> getMatchMessages(Long matchId) {
    List<Message>messages=this.messageRepository.findByMatchId(matchId);
        return messages;
    }
}
