package com.example.api.account.controller;

import com.example.api.account.model.AccountMeResponse;
import com.example.api.common.api.Api;
import com.example.api.common.error.ErrorCode;
import com.example.api.common.error.ErrorCodeIfs;
import com.example.api.common.error.UserErrorCode;
import com.example.api.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class AccountApiController {

    @GetMapping("/me")
    public Api<AccountMeResponse> me() {
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("A@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();

        var str = "안녕하세요.";

        try {
            var age = Integer.parseInt(str);
        } catch (Exception e) {
            throw new ApiException(ErrorCode.SERVER_ERROR, e, "사용자 me 호출 시 에러 발생");
        }

        return Api.OK(response);
    }

    @GetMapping("/error")
    public Api<Object> error() {
        var response = AccountMeResponse.builder()
                .name("홍길동")
                .email("A@gmail.com")
                .registeredAt(LocalDateTime.now())
                .build();

        return Api.ERROR(UserErrorCode.UER_NOT_FOUND, "사용자가 존재하지 않습니다.");
    }

}
