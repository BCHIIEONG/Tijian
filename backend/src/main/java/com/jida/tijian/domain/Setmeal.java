package com.jida.tijian.domain;

import java.util.List;

public class Setmeal {
    private Integer smId;

    private String name;

    private Integer type;

    private Integer price;

    private List<Checkitem> checkitemsList;

    public List<Checkitem> getCheckitemsList() {
        return checkitemsList;
    }

    public void setCheckitemsList(List<Checkitem> checkitemsList) {
        this.checkitemsList = checkitemsList;
    }

    public Integer getSmId() {
        return smId;
    }

    public void setSmId(Integer smId) {
        this.smId = smId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}