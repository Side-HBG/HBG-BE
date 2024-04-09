package com.devjin.springstu.domain.Global.Controller;


import com.devjin.springstu.domain.Exception.ApiException;
import com.devjin.springstu.domain.enums.ErrorCode;
import com.google.gson.stream.MalformedJsonException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ApiException.class)
    public ErrorCode handleApiException(ApiException ex) {return ex.getErrorCode();}
    @ExceptionHandler(Exception.class)
    public ErrorCode handleException(Exception ex) {return ErrorCode.INTER_SERVER_ERROR;}
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ErrorCode MediaException(HttpMediaTypeNotSupportedException es){return ErrorCode.MISSING_REQUEST;}
    @ExceptionHandler(MethodNotAllowedException.class)
    public ErrorCode handleMethodNotAllowedExceptionException(MethodNotAllowedException ex) {return ErrorCode.METHOD_NOT_SUPPORT;}
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ErrorCode handleHttpRequestMethodNotAllowedExceptionException(HttpRequestMethodNotSupportedException ex) {return ErrorCode.METHOD_NOT_SUPPORT;}
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ErrorCode handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {return ErrorCode.MISSING_REQUEST;}
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ErrorCode handleInsufficitentAuthenticationException(InsufficientAuthenticationException ex){return ErrorCode.JWT_AUTHENTICATION_REQUIRED;}
    @ExceptionHandler(io.jsonwebtoken.security.SecurityException.class)
    public ErrorCode handleSecurityException(io.jsonwebtoken.security.SecurityException ex) {return ErrorCode.JWT_INVAILDSIGNATURE;}
    @ExceptionHandler(MalformedJsonException.class)
    public ErrorCode handleMalformedJsonException(MalformedJsonException ex) {return ErrorCode.JWT_INVAILDSIGNATURE;}
    @ExceptionHandler(MalformedJwtException.class)
    public ErrorCode handleMalformedJwtException(MalformedJwtException ex) {return ErrorCode.JWT_INVAILDSIGNATURE;}
    @ExceptionHandler(ExpiredJwtException.class)
    public ErrorCode handleExpiredJwtException(ExpiredJwtException ex) {return ErrorCode.JWT_EXPIREDTOKEN;}
    @ExceptionHandler(UnsupportedJwtException.class)
    public ErrorCode handleUnsupportedJwtException(UnsupportedJwtException ex) {return ErrorCode.JWT_UNSUPPORTTOKEN;}
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorCode handleIllegalArgumentException(IllegalArgumentException ex) {return ErrorCode.JWT_ILLEGALARGUMENT;}
}
