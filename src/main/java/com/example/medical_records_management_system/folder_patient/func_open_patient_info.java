package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.data_patients;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_patient_info {

    public void showPatientInfo(AnchorPane right_pane, data_patients patient) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_patient_info.fxml"));
            AnchorPane patientInfoView = loader.load();

            // Получаем контроллер
            func_patient_info patientInfoController = loader.getController();

            // Передаем данные пациента
            patientInfoController.setPatientInfo(patient);

            right_pane.getChildren().clear();
            right_pane.getChildren().add(patientInfoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
