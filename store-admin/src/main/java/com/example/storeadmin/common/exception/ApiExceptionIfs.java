package com.example.storeadmin.common.exception;

import com.example.storeadmin.common.error.ErrorCodeIfs;

public interface ApiExceptionIfs {

    ErrorCodeIfs getErrorCodeIfs();

    String getErrorDescription();

}
