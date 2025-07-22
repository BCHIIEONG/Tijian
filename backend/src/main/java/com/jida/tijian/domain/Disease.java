package com.jida.tijian.domain;

public class Disease {
    private Integer disease_id;

    private String disease_name;

    private Integer categoryId;

    public Integer getDisease_id() {
        return disease_id;
    }

    public void setDisease_id(Integer disease_id) {
        this.disease_id = disease_id;
    }

    public String getDisease_name() {
        return disease_name;
    }

    public void setDisease_name(String disease_name) {
        this.disease_name = disease_name == null ? null : disease_name.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }
}