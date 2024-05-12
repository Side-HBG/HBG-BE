package com.devjin.springstu.domain.common;

public class ResponseMessage {
    public static final String OK ="OK";
    public static final String LOGIN_SUCCESS ="LOGIN_OK";

    public static final String LOGIN_FAIL ="LOGIN_FAIL";
    public static final String USER_READ_FAIL = "USER_SEARCH_FAIL";
    public static final String NOT_FOUND_USER = "NOT_FOUND_USER";
    public static final String USER_CREATE_FAIL = "USER_SIGN_FAIL";
    public static final String USER_UPDATE_FAIL = "USER_UPDATE_FAIL";
    public static final String USER_DELETE_FAIL = "USER_DELETE_FAIL";
    public static final String USER_VERIFICATION_FAIL = "USER_VERIFICATION_FAIL";
    public static final String DOWNLOAD_FAIL = "DOWNLOAD_FAIL";
    public static final String INTERNAL_SERVER_ERROR =  "INTERNAL_SERVER_ERROR";
    public static final String FAGE_NOT_FOUND = "PAGE_NOT_FOUND";
    public static final String ID_DUPLICATION = "ID_DUPLICATION";
    public static final String EMAIL_DUPLICATION = "EMAIL_DUPLICATION";
    public static final String PWD_NOT_VAILD = "PWD_NOT_VAILD";
    public static final String ID_NOT_FOUND = "ID_NOT_FOUND";
    public static final String PRODUCT_KEY_NOT_FOUND = "PRODUCT_KEY_NOT_FOUND";
    public static final String PRODUCT_KEY_NOT_USE = "PRODUCT_KEY_NOT_USE";
    public static final String PRODUCT_KEY_ALREADY_USE = "PRODUCT_KEY_ALREADY_USE";
    public static final String PRODUCT_TYPE_NOT_FOUND = "PRODUCT_TYPE_NOT_FOUND";
    public static final String PERMISSION_DENIED = "NO_PERMISSION_CHECK_TOKEN";
    public static final String PERMISSION_DENIED_ID ="NO_PERMISSION_CHECK_ACCOUNT";
    public static final String METHOD_NOT_SUPPORT = "METHOD_NOT_SUPPORT";
    public static final String MISSING_REQUEST = "MISSING_REQUEST";
    public static final String JWT_INVAILDSIGNATURE = "JWT_INVAILDSIGNATURE";
    public static final String JWT_EXPIREDTOKEN = "JWT_EXPIREDTOKEN";
    public static final String JWT_UNSUPPORTTOKEN ="JWT_UNSUPPORTTOKEN";
    public static final String JWT_ILLEGALARGUMENT="JWT_ILLEGALARGUMENT";
    public static final String JWT_TOKEN_NOT_FOUND="JWT_TOKEN_NOT_FOUND";
    public static final String ACCOUNT_NOT_FOUND="ACCOUNT_NOT_FOUND";
    public static final String JWT_AUTHENTICATION_REQUIRED ="JWT_AUTHENTICATION_REQUIRED";
    public static final String PAYMENT_NOT_FOUND = "PAYMENT_NOT_COUNT_CHECK_DATA";


    public static final String STEAM_NOT_FONUD_APPNAME = "STEAM_NOT_FOUND_APPNAME";
    public static final String STEAM_NOT_INVAILD_JSON_STRING = "STEAM_NOT_INVAILD_JSON_STRING";

    /*
    OK Code
    Global Response Controller 사용후 더이상 사용하지 않음.

    public static final String USER_READ = "USER_SEARCH_OK";
    public static final String USER_CREATE = "USER_SIGN_OK";
    public static final String USER_UPDATE = "USER_UPDATE_OK";
    public static final String USER_DELETE = "USER_DELETE_OK";
    public static final String DB_ERROR = "DB_ERROR";
    public static final String DOWNLOAD_SUCCESS = "DOWNLOAD_OK";
    public static final String PRODUCT_KEY_ADD_OK = "PRODUCT_KEY_ADD_OK";
    public static final String PASSWORD_CHANGE_OK = "PASSWORD_CHANGE_OK";

     */
}
