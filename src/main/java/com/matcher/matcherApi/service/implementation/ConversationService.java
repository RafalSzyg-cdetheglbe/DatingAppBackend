package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Conversation;
import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.repository.ConversationRepository;
import com.matcher.matcherApi.repository.MatchRepository;
import com.matcher.matcherApi.service.interfaces.IConversationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationService implements IConversationService {
    private final ConversationRepository conversationRepository;
    private final MatchRepository matchRepository;


    public ConversationService(ConversationRepository conversationRepository, MatchRepository matchRepository) {
        this.conversationRepository = conversationRepository;
        this.matchRepository = matchRepository;
    }
    @Override
    public void addConversation(Long matchId) {
        Conversation conversation = new Conversation();
        Match matchOrg= new Match();
        List<Match> matches=this.matchRepository.findByMatchId(matchId);
        for(Match match:matches){
            if(match.getId().equals(matchId)){matchOrg=match;break;}
        }
        System.out.println(matchOrg.getId());
        conversation.setMatch(matchOrg);
        conversation.setDeleted(false);
        this.conversationRepository.save(conversation);
    }
}
