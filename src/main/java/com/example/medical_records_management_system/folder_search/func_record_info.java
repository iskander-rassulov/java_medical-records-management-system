package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_data.data_medical_records;
import com.example.medical_records_management_system.folder_data.data_patients;
import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.example.medical_records_management_system.folder_patient.func_open_patient_info;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class func_record_info {
    @FXML
    public Text valRecordId;
    @FXML
    public Text valPatientId;
    @FXML
    public Text valPatientName;
    @FXML
    public Text valDoctorId;
    @FXML
    public Text valDoctorName;
    @FXML
    public Text valVisitDate;
    @FXML
    public Text valDiagnosis;
    @FXML
    public Text valTreatmentPlan;

    @FXML
    public JFXButton buttonOpenPatientCard;

    database_handler databaseHandler = new database_handler();


    public void initialize(data_medical_records dataMedicalRecords, AnchorPane rightPane){

        buttonOpenPatientCard.setOnMouseClicked(event -> {
              // Двойной щелчок
                data_patients selectedPatient = databaseHandler.getPatientById(dataMedicalRecords.getPatientId());
                if (selectedPatient != null) {
                    System.out.println("Пациент выбран: " + selectedPatient.getFirstName());  // Проверка
                    func_open_patient_info patientInfoOpener = new func_open_patient_info();
                    assert rightPane != null;
                    patientInfoOpener.showPatientInfo(rightPane, selectedPatient);  // Передаем right_pane
                }
            }
        );

    }

    public void setRecordInfo(data_medical_records dataMedicalRecords){
        valRecordId.setText(String.valueOf(dataMedicalRecords.getMedicalRecordId()));
        valPatientId.setText(String.valueOf(dataMedicalRecords.getPatientId()));
        valDoctorId.setText(String.valueOf(dataMedicalRecords.getDoctorId()));
        valVisitDate.setText(dataMedicalRecords.getVisitDate());
        valDiagnosis.setText(dataMedicalRecords.getDiagnosis());
        valTreatmentPlan.setText(dataMedicalRecords.getTreatmentPlan());
        docAndPatientNames(dataMedicalRecords.getPatientId());
    }

    public void docAndPatientNames(int patientId) {
        // Получаем данные доктора по его ID
        data_doctor doctor = func_check_login_data.getDoctorData();

        // Получаем данные пациента по его ID
        data_patients patient = databaseHandler.getPatientById(patientId);

        // Устанавливаем имя доктора
        if (doctor != null) {
            valDoctorName.setText(doctor.getFirstName().trim() + " " + doctor.getLastName().trim());
        } else {
            valDoctorName.setText("Доктор не найден");
        }

        // Устанавливаем имя пациента
        if (patient != null) {
            valPatientName.setText(patient.getFirstName() + " " + patient.getSecondName());
        } else {
            valPatientName.setText("Пациент не найден");
        }
    }

}
