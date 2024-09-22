package com.example.medical_records_management_system.folder_main;

import com.example.medical_records_management_system.folder_logged_in.func_keep_logged_in;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class func_log_out {

    public void logOut(Stage stage) {
        try {
            // Сбрасываем данные "Keep me logged in"
            func_keep_logged_in.forgetUserData();

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


