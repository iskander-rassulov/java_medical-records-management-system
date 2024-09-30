package com.example.medical_records_management_system.folder_settings;

import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class func_open_settings {

    public void showSettings(AnchorPane center_pane) {
        try {
            // Загружаем view_calendar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_settings.fxml"));
            AnchorPane openSearchView = loader.load();  // Загружаем AnchorPane с календарем

            // Очищаем содержимое center_pane и добавляем календарь
            center_pane.getChildren().clear();
            center_pane.getChildren().add(openSearchView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
