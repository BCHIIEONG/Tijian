package com.jida.tijian.exception;

/**
 * 定义针对体检APP的异常类型
 */
public class TijianAPPException extends RuntimeException{

    private Integer code;     //status
    private String message;   //desc

    public TijianAPPException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public TijianAPPException() {

    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
