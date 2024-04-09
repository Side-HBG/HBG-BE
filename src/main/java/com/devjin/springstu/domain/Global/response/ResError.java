package com.devjin.springstu.domain.Global.response;

import com.devjin.springstu.domain.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ResError {

    private final boolean result;
    private final int statusCode;
    private final String responseMessage;
    private final String errcode;

    public static ResError ex(ErrorCode errorCode){
        return ResError.builder()
                .result(false)
                .statusCode(errorCode.getStatus())
                .responseMessage(errorCode.getMessage())
                .errcode(errorCode.getErrorCode())
                .build();
    }
}
