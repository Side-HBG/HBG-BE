package com.devjin.springstu.domain.Global.Controller;


import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.enums.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ApiException.class)
    public ErrorCode handleApiException(ApiException ex) {return ex.getErrorCode();}
    @ExceptionHandler(Exception.class)
    public ErrorCode handleException(Exception ex) {

        System.out.println(ex.getMessage());
        Arrays.stream(ex.getStackTrace()).toList().forEach(fe-> System.out.println(fe.toString()));

        return ErrorCode.INTER_SERVER_ERROR;}
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorCode MediaException(HttpMediaTypeNotSupportedException es){return ErrorCode.MISSING_REQUEST;}
    @ExceptionHandler(MethodNotAllowedException.class)
    public ErrorCode handleMethodNotAllowedExceptionException(MethodNotAllowedException ex) {return ErrorCode.METHOD_NOT_SUPPORT;}
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorCode handleHttpRequestMethodNotAllowedExceptionException(HttpRequestMethodNotSupportedException ex) {return ErrorCode.METHOD_NOT_SUPPORT;}
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorCode handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {return ErrorCode.MISSING_REQUEST;}
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ErrorCode handleInsufficitentAuthenticationException(InsufficientAuthenticationException ex){
        return ErrorCode.JWT_AUTHENTICATION_REQUIRED;}
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorCode handleIllegalArgumentException(IllegalArgumentException ex) {
        System.out.println(ex.getMessage());
        Arrays.stream(ex.getStackTrace()).toList().forEach(fe-> System.out.println(fe.toString()));
        return ErrorCode.JWT_ILLEGALARGUMENT;}
}
