package com.example.discordbackend.exception;

import org.springframework.http.HttpStatus;

public enum UserManageExceptionType implements BaseExceptionType {
    DUPLICATED_SIGNUP_USERNAME(600, HttpStatus.OK, "이미 존재하는 아이디입니다.");

    private int errorCode;
    private HttpStatus httpStatus;
    private String errMsg;

    UserManageExceptionType(int errorCode, HttpStatus httpStatus, String errMsg) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
            return this.errorCode;
    }

    @Override
    public HttpStatus getHttpStatus() {
            return this.httpStatus;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }
}
