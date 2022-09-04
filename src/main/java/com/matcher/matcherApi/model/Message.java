package com.matcher.matcherApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="MESSAGE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "match_id")
    public Match match;
    public String content;
    public LocalDateTime sendDate;
    public boolean isDeleted;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
