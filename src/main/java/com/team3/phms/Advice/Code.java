package com.team3.phms.Advice;

public enum Code {
    RC100(200,"success"),
    RC500(500,"fail"),

    INVALID_TOKEN(2001,"invalid_token"),
    ACCESS_DENIED(2003,"access denied"),
    USERNAME_OR_PASSWORD_ERROR(1002,"username or password wrong");

    private final int code;
    private final String message;

    Code(int code, String message){
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}