package com.matcher.matcherApi.DTO;

import lombok.Data;

@Data
public class MatchDTO {
    private Long id;
    private Long user;
    private boolean isDeleted;
}
