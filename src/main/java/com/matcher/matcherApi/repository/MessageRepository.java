package com.matcher.matcherApi.repository;


import com.matcher.matcherApi.model.Match;
import com.matcher.matcherApi.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    final String queryMatchMessages= "SELECT m.* FROM message m  WHERE m.match_id=:id";

    @Query(value = queryMatchMessages, nativeQuery = true)
    List<Message> findByMatchId(Long id);
}
