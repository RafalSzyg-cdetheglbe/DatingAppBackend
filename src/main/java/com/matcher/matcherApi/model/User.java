package com.matcher.matcherApi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="USERS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String email;
    public String password;
    public String firstName;
    public String lastName;
    private int age;
    @ManyToOne
    @JoinColumn(name = "user_role")
    public UserRole userRole;
    public String gender;
    @ManyToOne
    @JoinColumn(name = "gender_interest")
    public GenderInterest genderInterest;
    public boolean isDeleted;

}
