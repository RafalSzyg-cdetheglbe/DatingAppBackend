package com.matcher.matcherApi.repository;

import com.matcher.matcherApi.model.GenderInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderInterestRepository extends JpaRepository<GenderInterest,Long> {
}
