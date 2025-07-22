package com.jida.tijian.domain;

public class DiseaseWithBLOBs extends Disease {
    private String etiology;

    private String clinical_manifestations;

    private String complications;

    private String treatment;

    private String nursing_care;

    public String getEtiology() {
        return etiology;
    }

    public void setEtiology(String etiology) {
        this.etiology = etiology == null ? null : etiology.trim();
    }

    public String getClinical_manifestations() {
        return clinical_manifestations;
    }

    public void setClinical_manifestations(String clinical_manifestations) {
        this.clinical_manifestations = clinical_manifestations == null ? null : clinical_manifestations.trim();
    }

    public String getComplications() {
        return complications;
    }

    public void setComplications(String complications) {
        this.complications = complications == null ? null : complications.trim();
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment == null ? null : treatment.trim();
    }

    public String getNursing_care() {
        return nursing_care;
    }

    public void setNursing_care(String nursing_care) {
        this.nursing_care = nursing_care == null ? null : nursing_care.trim();
    }
}