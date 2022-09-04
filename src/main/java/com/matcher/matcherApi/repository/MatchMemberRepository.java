package com.matcher.matcherApi.repository;

import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.MatchMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMemberRepository extends JpaRepository<MatchMember,Long> {

    final String queryUserMembers="SELECT m.* FROM match_member m WHERE m.user_id=:id";
    @Query(value=queryUserMembers,nativeQuery = true)
    List<MatchMember> findByUserId(Long id);
}
