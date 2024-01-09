package com.example.api.domain.token.service;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.exception.ApiException;
import com.example.api.domain.token.ifs.TokenHelperIfs;
import com.example.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenHelperIfs tokenHelperIfs;

    public TokenDto issueAccessToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelperIfs.issueAccessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelperIfs.issueRefreshToken(data);
    }

    public Long validationToken(String token) {
        Map<String, Object> data = tokenHelperIfs.validationTokenWithThrow(token);
        Long userId = Long.parseLong(data.get("userId").toString());

        Objects.requireNonNull(userId, () -> { throw new ApiException(ErrorCode.NULL_POINT); });

        return userId;
    }

}
