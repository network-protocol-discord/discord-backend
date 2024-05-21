package com.example.discordbackend.exception;

import org.springframework.http.HttpStatus;

public enum UserManageExceptionType implements BaseExceptionType {

    //TODO: HttpStatus OK가 맞나..??
    DUPLICATED_SIGNUP_USERNAME(600, HttpStatus.CONFLICT, "이미 존재하는 아이디입니다."),

    DUPLICATED_SIGNUP_NICKNAME(601, HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다.");

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
