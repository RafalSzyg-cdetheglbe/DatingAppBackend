package com.matcher.matcherApi.repository;

import com.matcher.matcherApi.model.MatchMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchMemberRepository extends JpaRepository<MatchMember,Long> {
}
