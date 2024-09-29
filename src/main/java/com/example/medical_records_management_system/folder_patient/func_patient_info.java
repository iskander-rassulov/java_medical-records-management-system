package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.folder_data.data_medical_records;
import com.example.medical_records_management_system.folder_data.data_patients;
import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_search.func_open_record_info;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
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
    public JFXButton button_open_medical_record;

    database_handler databaseHandler = new database_handler();


    public void initialize(data_patients patient, AnchorPane rightPane){

        button_open_medical_record.setOnMouseClicked(event -> {
                    // Двойной щелчок
                    data_medical_records selectedRecord = databaseHandler.getMedicalRecordByPatientId(patient.getPatientId());
                    if (selectedRecord != null) {
                        System.out.println("Запись выбрана: " + selectedRecord.getMedicalRecordId());  // Проверка
                        func_open_record_info recordInfo = new func_open_record_info();
                        assert rightPane != null;
                        recordInfo.showRecordInfo(rightPane, selectedRecord);  // Передаем right_pane
                    }
                }
        );
    }

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
