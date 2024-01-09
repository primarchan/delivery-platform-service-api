package com.example.api.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * JWT Token 의 경우, 2000 번 대 에러 코드 사용
 */
@Getter
@AllArgsConstructor
public enum TokenErrorCode implements ErrorCodeIfs {

    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2000, "유효하지 않은 TOKEN"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "만료된 TOKEN"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST.value(), 2002, "알 수 없는 TOKEN")
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
