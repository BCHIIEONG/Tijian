package com.jida.tijian.domain;

public class Examine {
    private Integer examineId;

    private String examineName;

    private Integer projectId;

    private String examineDepartment;

    private String abbreviation;

    private String common_names;

    private String examineObjective;

    public Integer getExamineId() {
        return examineId;
    }

    public void setExamineId(Integer examineId) {
        this.examineId = examineId;
    }

    public String getExamineName() {
        return examineName;
    }

    public void setExamineName(String examineName) {
        this.examineName = examineName == null ? null : examineName.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getExamineDepartment() {
        return examineDepartment;
    }

    public void setExamineDepartment(String examineDepartment) {
        this.examineDepartment = examineDepartment == null ? null : examineDepartment.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public String getCommon_names() {
        return common_names;
    }

    public void setCommon_names(String common_names) {
        this.common_names = common_names == null ? null : common_names.trim();
    }

    public String getExamineObjective() {
        return examineObjective;
    }

    public void setExamineObjective(String examineObjective) {
        this.examineObjective = examineObjective == null ? null : examineObjective.trim();
    }
}