package com.example.api.domain.token.controller.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private LocalDateTime accessTokenExpiredAt;
    private String refreshToken;
    private LocalDateTime refreshTokenExpiredAt;

}
