package com.jida.tijian.dto;

public class ResponseObject<T> {

    private Integer status;

    private String desc;

    T data;

    public static final  Integer SUCCESS_STATUS=200;
    public static final String SUCCESS_DESC="成功";

    public ResponseObject(Integer status, String desc, T data) {
        this.desc = desc;
        this.status = status;
        this.data = data;
    }

    public ResponseObject(Integer status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
