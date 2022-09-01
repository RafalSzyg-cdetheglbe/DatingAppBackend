package com.matcher.matcherApi.DTO;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Long userRole;
    private Long genderInterest;
    private String gender;
    private boolean isDeleted;

}
