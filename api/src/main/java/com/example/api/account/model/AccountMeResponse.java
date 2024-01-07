package com.example.api.account.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
// @JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AccountMeResponse {

    private String email;
    private String name;
    private LocalDateTime registeredAt;

}
