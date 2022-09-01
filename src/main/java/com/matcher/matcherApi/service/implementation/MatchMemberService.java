package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.MatchMember;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.repository.MatchMemberRepository;
import com.matcher.matcherApi.service.interfaces.IMatchMemberService;
import org.springframework.stereotype.Service;

@Service
public class MatchMemberService implements IMatchMemberService {
    private final MatchMemberRepository matchMemberRepository;

    public MatchMemberService(MatchMemberRepository matchMemberRepository) {
        this.matchMemberRepository = matchMemberRepository;
    }

    public void addMatchMember(Long userId,Long matchId){
        MatchMember matchMember=new MatchMember();
        User user=new User();
        Match match=new Match();
        user.setId(userId);
        match.setId(matchId);

        matchMember.setUser(user);
        matchMember.setMatch(match);
        this.matchMemberRepository.save(matchMember);

    }

}
