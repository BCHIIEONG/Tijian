package com.jida.tijian.exception;

public enum TijianAPPExceptionEnum {
    MOBILE_NOT_REGISTER(2001,"手机号尚未注册"),USERID_OR_PASSWORD_IS_WRONG(2002,"手机号或者密码错误"),SYSTEM_ERROR(2003,"系统错误");

    TijianAPPExceptionEnum() {

    }

    TijianAPPExceptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
