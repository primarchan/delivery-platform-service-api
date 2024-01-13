package com.example.storeadmin.exceptionhandler;

import com.example.storeadmin.common.api.Api;
import com.example.storeadmin.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(value = Integer.MAX_VALUE)  // 가장 마지막에 실행 적용
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception exception) {
        log.error("", exception);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
                .body(Api.ERROR(ErrorCode.SERVER_ERROR));
    }

}
