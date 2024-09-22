package com.example.medical_records_management_system.folder_main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class func_log_out {

    public void logOut(Stage stage) {
        try {
            // Загружаем view_login_page.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_login_page.fxml"));
            Parent root = loader.load();

            // Устанавливаем сцену на stage
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

