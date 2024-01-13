package com.example.storeadmin.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * User 의 경우, 1000 번 대 에러 코드 사용
 */
@Getter
@AllArgsConstructor
public enum UserErrorCode implements ErrorCodeIfs {

    UER_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), 1404, "사용자를 찾을 수 없음"),
    ;

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
