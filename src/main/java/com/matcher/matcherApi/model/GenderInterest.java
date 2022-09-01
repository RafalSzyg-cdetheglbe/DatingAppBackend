package com.matcher.matcherApi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "GENDER_INTEREST")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenderInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    private String name;
    private String description;
}
