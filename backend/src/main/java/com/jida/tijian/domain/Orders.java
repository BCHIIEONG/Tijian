package com.jida.tijian.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Orders {
    private Integer orderId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Shanghai")
    private Date orderDate;

    private String userId;

    private Integer hpId;

    private Integer smId;

    private Integer state;
    
    // 临时字段，用于存储用户姓名，不映射到数据库
    private String name;
    
    // 关联的套餐信息
    private Setmeal setmeal;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getHpId() {
        return hpId;
    }

    public void setHpId(Integer hpId) {
        this.hpId = hpId;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Setmeal getSetmeal() {
        return setmeal;
    }

    public void setSetmeal(Setmeal setmeal) {
        this.setmeal = setmeal;
    }
}