package com.example.medical_records_management_system.folder_manage_records;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_manage_records_view {
    public void showRecords(AnchorPane center_pane) {
        try {
            // Загружаем view_calendar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_manage_records.fxml"));
            AnchorPane openSearchView = loader.load();  // Загружаем AnchorPane с календарем

            // Очищаем содержимое center_pane и добавляем календарь
            center_pane.getChildren().clear();
            center_pane.getChildren().add(openSearchView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
