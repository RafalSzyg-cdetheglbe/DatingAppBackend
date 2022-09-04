package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.model.MatchMember;

import java.util.List;

public interface IMatchMemberService {
    void addMatchMember(Long userId, Long matchId);

    List<MatchMember> getUserMembers(Long id);
}
