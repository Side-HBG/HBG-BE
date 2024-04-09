package com.devjin.springstu.domain.Global.Controller;


import com.devjin.springstu.domain.Global.response.ResError;
import com.devjin.springstu.domain.Global.response.ResResult;
import com.devjin.springstu.domain.common.ResponseMessage;
import com.devjin.springstu.domain.common.StatusCode;
import com.devjin.springstu.domain.enums.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class GlobalResponseController implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {

        //Exception Handle
        if(body instanceof ErrorCode) return ResError.ex((ErrorCode) body);

        // Result Handle
        if(body instanceof ResResult)return body;

        // Default Handle
        return ResResult.res(true, StatusCode.OK, ResponseMessage.OK,body);
    }
}

