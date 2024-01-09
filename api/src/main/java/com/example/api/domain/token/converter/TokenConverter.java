package com.example.api.domain.token.converter;

import com.example.api.annotation.Converter;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.token.controller.model.TokenResponse;
import com.example.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {

    public TokenResponse toResponse(TokenDto accessToken, TokenDto refreshToken) {
        Objects.requireNonNull(accessToken, () -> { throw new ApiException(ErrorCode.NULL_POINT); });
        Objects.requireNonNull(refreshToken, () -> { throw new ApiException(ErrorCode.NULL_POINT); });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }



}
