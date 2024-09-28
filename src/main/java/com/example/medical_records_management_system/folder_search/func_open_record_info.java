package com.example.medical_records_management_system.folder_search;

import com.example.medical_records_management_system.data_medical_records;
import com.example.medical_records_management_system.data_patients;
import com.example.medical_records_management_system.folder_patient.func_patient_info;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_record_info {

    public void showRecordInfo(AnchorPane right_pane, data_medical_records dataMedicalRecords) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_record_info.fxml"));
            AnchorPane recordInfoView = loader.load();

            // Получаем контроллер
            func_record_info recordInfo = loader.getController();
            recordInfo.initialize(dataMedicalRecords, right_pane);
            // Передаем данные пациента
            recordInfo.setRecordInfo(dataMedicalRecords);

            right_pane.getChildren().clear();
            right_pane.getChildren().add(recordInfoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
