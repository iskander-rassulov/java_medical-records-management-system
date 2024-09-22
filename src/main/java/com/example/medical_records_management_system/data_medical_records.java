package com.example.medical_records_management_system;

import java.sql.Date;

public class data_medical_records {


    private int medicalRecordId;
    private int patientId;
    private int doctorId;
    private String visitDate;
    private String diagnosis;
    private String treatmentPlan;

    // Геттеры
    public int getMedicalRecordId() {
        return medicalRecordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    // Сеттеры
    public void setMedicalRecordId(int medicalRecordId) {
        this.medicalRecordId = medicalRecordId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }


}
