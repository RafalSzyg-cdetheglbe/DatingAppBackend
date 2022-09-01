package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.repository.MatchMemberRepository;
import com.matcher.matcherApi.repository.MatchRepository;
import com.matcher.matcherApi.repository.UserRepository;
import com.matcher.matcherApi.service.interfaces.IMatchService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MatchService implements IMatchService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private  final MatchMemberRepository matchMemberRepository;
    private final MatchMemberService matchMemberService;
    private final ConversationService conversationService;

    public MatchService(UserRepository userRepository, MatchRepository matchRepository, MatchMemberRepository matchMemberRepository, MatchMemberService matchMemberService, ConversationService conversationService) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.matchMemberRepository = matchMemberRepository;
        this.matchMemberService = matchMemberService;
        this.conversationService = conversationService;
    }

    @Transactional
    public void addMatch(Long id, String email){

        User user=this.userRepository.findUserByEmail(email);
        Match match=new Match(user,true);

        this.matchRepository.save(match);
        this.matchMemberService.addMatchMember(id,match.getId());
        this.conversationService.addConversation(match.getId());
    }
}
