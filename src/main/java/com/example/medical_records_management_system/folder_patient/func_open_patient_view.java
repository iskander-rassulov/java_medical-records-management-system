package com.example.medical_records_management_system.folder_patient;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_patient_view {
    public void showPatients(AnchorPane center_pane) {
        try {
            // Загружаем view_calendar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_patient.fxml"));
            AnchorPane openSearchView = loader.load();  // Загружаем AnchorPane с календарем

            // Очищаем содержимое center_pane и добавляем календарь
            center_pane.getChildren().clear();
            center_pane.getChildren().add(openSearchView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
