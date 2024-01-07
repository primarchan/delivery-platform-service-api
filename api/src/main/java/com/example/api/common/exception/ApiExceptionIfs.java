package com.example.api.common.exception;

import com.example.api.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();

}
