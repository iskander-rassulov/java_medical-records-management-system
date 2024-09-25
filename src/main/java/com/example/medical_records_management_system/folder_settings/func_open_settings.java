package com.example.medical_records_management_system.folder_settings;

import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.example.medical_records_management_system.folder_main.controller_main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class func_open_settings {
    public void switchToSettingsView(Stage stage) {
        try {
            // Загружаем view_main.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_settings.fxml"));
            Parent root = loader.load();

            // Получаем контроллер для главного окна
            controller_main mainController = loader.getController();

            // Передаем данные о докторе в контроллер
            mainController.setDoctorData(func_check_login_data.getDoctorData());

            // Устанавливаем сцену
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
