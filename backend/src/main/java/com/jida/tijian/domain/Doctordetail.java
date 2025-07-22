package com.jida.tijian.domain;

public class Doctordetail {
    private Integer doctorId;

    private String doctorName;

    private String doctorLevel;

    private String doctorDepartment;

    private String doctorHospital;

    private String doctorExcel;

    private String doctorSynopsis;

    private String doctorPicture;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDoctorLevel() {
        return doctorLevel;
    }

    public void setDoctorLevel(String doctorLevel) {
        this.doctorLevel = doctorLevel == null ? null : doctorLevel.trim();
    }

    public String getDoctorDepartment() {
        return doctorDepartment;
    }

    public void setDoctorDepartment(String doctorDepartment) {
        this.doctorDepartment = doctorDepartment == null ? null : doctorDepartment.trim();
    }

    public String getDoctorHospital() {
        return doctorHospital;
    }

    public void setDoctorHospital(String doctorHospital) {
        this.doctorHospital = doctorHospital == null ? null : doctorHospital.trim();
    }

    public String getDoctorExcel() {
        return doctorExcel;
    }

    public void setDoctorExcel(String doctorExcel) {
        this.doctorExcel = doctorExcel == null ? null : doctorExcel.trim();
    }

    public String getDoctorSynopsis() {
        return doctorSynopsis;
    }

    public void setDoctorSynopsis(String doctorSynopsis) {
        this.doctorSynopsis = doctorSynopsis == null ? null : doctorSynopsis.trim();
    }

    public String getDoctorPicture() {
        return doctorPicture;
    }

    public void setDoctorPicture(String doctorPicture) {
        this.doctorPicture = doctorPicture == null ? null : doctorPicture.trim();
    }
}