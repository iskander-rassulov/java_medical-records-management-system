package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.data_patients;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class func_patient_info {
    @FXML
    public Text valName;
    @FXML
    public Text valSurname;
    @FXML
    public Text valDateOfBirth;
    @FXML
    public Text valGender;
    @FXML
    public Text valPhoneNumber;
    @FXML
    public Text valEmail;
    @FXML
    public Text valAddress;
    @FXML
    public Text valRecordId;
    @FXML
    public JFXButton button_patient_manage_patient_info;
    @FXML
    public JFXButton button_patient_open_medical_record;

    // Метод для установки данных о докторе
    public void setPatientInfo(data_patients patient) {
        valName.setText(patient.getFirstName()+" ");
        valSurname.setText(patient.getSecondName());
        valDateOfBirth.setText(patient.getDateOfBirth());
        valGender.setText(patient.getGender());
        valPhoneNumber.setText(patient.getPhoneNumber());
        valEmail.setText(patient.getEmail());
        valAddress.setText(patient.getAddress());
        valRecordId.setText(String.valueOf(patient.getMedicalRecordId()));

        // Если необходимо, добавьте остальные поля (например, дату рождения, пол и др.)
        // valDateOfBirth.setText(...); // Пример использования
        // valGender.setText(...); // Пример использования
    }

}
