package com.jida.tijian.dto;

public class ResponseObject<T> {

    private Integer status;
    
    private Integer code;

    private String desc;

    T data;

    public static final  Integer SUCCESS_STATUS=200;
    public static final  Integer SUCCESS_CODE=1;
    public static final String SUCCESS_DESC="成功";

    public ResponseObject(Integer status, String desc, T data) {
        this.desc = desc;
        this.status = status;
        this.data = data;
        this.code = status == SUCCESS_STATUS ? SUCCESS_CODE : 0;
    }

    public ResponseObject(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
        this.code = status == SUCCESS_STATUS ? SUCCESS_CODE : 0;
    }
    
    public ResponseObject(Integer status, Integer code, String desc, T data) {
        this.status = status;
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
