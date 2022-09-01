package com.matcher.matcherApi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="MATCHES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
    public boolean isDeleted;

    public Match(User user, boolean b) {
        this.setUser(user);
        this.setDeleted(b);
    }
}
