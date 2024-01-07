package com.example.api.common.error;

public interface ErrorCodeIfs {

    Integer getHttpStatusCode();

    Integer getErrorCode();

    String getDescription();

}
