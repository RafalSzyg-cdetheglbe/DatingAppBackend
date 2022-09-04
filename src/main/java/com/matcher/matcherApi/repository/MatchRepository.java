package com.matcher.matcherApi.repository;

import com.matcher.matcherApi.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchRepository extends JpaRepository<Match,Long> {

    final String queryConversationMach = "SELECT c.* FROM matches c  WHERE c.id=:id";

    @Query(value = queryConversationMach, nativeQuery = true)
    List<Match> findByMatchId(Long id);

    final String queryUserMatches="SELECT m.* FROM matches m WHERE m.user_id=:id";
    @Query(value=queryUserMatches,nativeQuery = true)
    List<Match>findByUserId(Long id);

}
