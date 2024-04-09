package com.devjin.springstu.domain.Global.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class ResResult<T> {
    private final boolean result;
    private final int statusCode;
    private final String responseMessage;
    private final T data;

    public static<T> ResResult<T> res (final boolean result, final int statusCode, final String responseMessage) {
        return res(result,statusCode,responseMessage,null);
    }
    public static<T> ResResult<T> res (final boolean result, final int statusCode, final String responseMessage, final T data) {
        return ResResult.<T>builder()
                .data(data)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .result(result)
                .build();
    }

}
