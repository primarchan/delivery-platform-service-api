package com.example.api.common.api;

import com.example.api.common.error.ErrorCode;
import com.example.api.common.error.ErrorCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer resultCode;

    private String resultMessage;

    private String resultDescription;

    public static Result OK() {

        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs) {

        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, Throwable throwable) {

        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(throwable.getLocalizedMessage())
                .build();
    }

    public static Result ERROR(ErrorCodeIfs errorCodeIfs, String description) {

        return Result.builder()
                .resultCode(errorCodeIfs.getErrorCode())
                .resultMessage(errorCodeIfs.getDescription())
                .resultDescription(description)
                .build();
    }

}
