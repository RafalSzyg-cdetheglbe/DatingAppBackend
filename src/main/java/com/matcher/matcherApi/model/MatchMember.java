package com.matcher.matcherApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="MATCH_MEMBER")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "users")
    public User user;
    @ManyToOne
    @JoinColumn(name = "match")
    public Match match;
    public boolean isDeleted;
}
