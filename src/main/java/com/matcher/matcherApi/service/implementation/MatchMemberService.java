package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.MatchMember;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.repository.MatchMemberRepository;
import com.matcher.matcherApi.service.interfaces.IMatchMemberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MatchMemberService implements IMatchMemberService {
    private final MatchMemberRepository matchMemberRepository;

    public MatchMemberService(MatchMemberRepository matchMemberRepository) {
        this.matchMemberRepository = matchMemberRepository;
    }
@Override
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

    @Override
    public List<MatchMember> getUserMembers(Long id) {
        List<MatchMember>allmember=this.matchMemberRepository.findAll();
        allmember.removeIf(mm -> !mm.getMatch().getUser().getId().equals(id));
        System.out.println(allmember);

        List<MatchMember> matchMembers=this.matchMemberRepository.findByUserId(id);

        List<MatchMember>mergedMembers= Stream.concat(allmember.stream(),matchMembers.stream()).collect(Collectors.toList());
        return mergedMembers;
    }
}
