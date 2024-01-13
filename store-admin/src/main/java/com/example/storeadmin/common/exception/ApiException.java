package com.example.storeadmin.common.exception;

import com.example.storeadmin.common.error.ErrorCodeIfs;
import lombok.Getter;

@Getter
public class ApiException  extends RuntimeException implements ApiExceptionIfs {

    private final ErrorCodeIfs errorCodeIfs;
    private final String errorDescription;


    public ApiException(ErrorCodeIfs errorCodeIfs) {
        super(errorCodeIfs.getDescription());
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, String errorDescription) {
        super(errorDescription);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable cause) {
        super(cause);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorCodeIfs.getDescription();
    }

    public ApiException(ErrorCodeIfs errorCodeIfs, Throwable cause, String errorDescription) {
        super(cause);
        this.errorCodeIfs = errorCodeIfs;
        this.errorDescription = errorDescription;
    }

}
