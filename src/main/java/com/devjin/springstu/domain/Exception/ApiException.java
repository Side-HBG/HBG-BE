package com.devjin.springstu.domain.Exception;

import com.devjin.springstu.domain.enums.ErrorCode;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException{
    private final ErrorCode errorCode;
    public ApiException(ErrorCode errorCode){


        super(errorCode.getMessage());
        this.errorCode= errorCode;
    }
}
