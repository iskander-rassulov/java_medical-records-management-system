package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.data_patients;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_patient_info {

    public void showPatientInfo(AnchorPane right_pane, data_patients patient) {
        if (right_pane == null) {
            System.out.println("Error: right_pane is null в func_open_patient_info");
            return;
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_patient_info.fxml"));
            AnchorPane patientInfoView = loader.load();

            right_pane.getChildren().clear();
            right_pane.getChildren().add(patientInfoView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
