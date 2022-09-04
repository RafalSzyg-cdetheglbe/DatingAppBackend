package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.DTO.MatchDTO;
import com.matcher.matcherApi.model.Match;

import java.util.List;

public interface IMatchService {
    MatchDTO addMatch(Long userid, Long authorid);

    List<Match> getUserMatches(Long id);

    List<Match> getMatches();
}
