package com.example.discordbackend.exception;

public abstract class BaseException extends RuntimeException {
    public abstract BaseException getBaseException();
}
