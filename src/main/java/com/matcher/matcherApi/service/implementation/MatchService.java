package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.DTO.MatchDTO;
import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.MatchMember;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.repository.MatchMemberRepository;
import com.matcher.matcherApi.repository.MatchRepository;
import com.matcher.matcherApi.repository.UserRepository;
import com.matcher.matcherApi.service.interfaces.IMatchMemberService;
import com.matcher.matcherApi.service.interfaces.IMatchService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class MatchService implements IMatchService {
    private final UserRepository userRepository;
    private final MatchRepository matchRepository;
    private  final MatchMemberRepository matchMemberRepository;
    private final IMatchMemberService imatchMemberService;


    public MatchService(UserRepository userRepository, MatchRepository matchRepository, MatchMemberRepository matchMemberRepository,
                        IMatchMemberService imatchMemberService) {
        this.userRepository = userRepository;
        this.matchRepository = matchRepository;
        this.matchMemberRepository = matchMemberRepository;
        this.imatchMemberService = imatchMemberService;

    }

    @Transactional
    public MatchDTO addMatch(Long userid, Long authorid){
    List<MatchMember>matchMembers=this.matchMemberRepository.findByUserId(userid);
    for(MatchMember m:matchMembers){
        if(m.getMatch().getUser().getId().equals(authorid))return null;

    }
        System.out.println(authorid);
        User user= new User();
        user.setId(authorid);
        Match match=new Match(user,true);
        this.matchRepository.save(match);

       this.imatchMemberService.addMatchMember(userid,match.getId());

        MatchDTO matchDTO=new MatchDTO();
        matchDTO.setId(match.getId());
        matchDTO.setUser(userid);
        matchDTO.setDeleted(false);
        return matchDTO;
    }

    @Override
    public List<Match> getUserMatches(Long id) {
        List<Match>matches=this.matchRepository.findByUserId(id);

        return matches;
    }

    @Override
    public List<Match> getMatches() {

        return this.matchRepository.findAll();
    }
}
