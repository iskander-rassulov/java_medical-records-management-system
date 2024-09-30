package com.example.medical_records_management_system.folder_sign_up;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class func_open_sign_up_done {

    public void showSignUpDone(AnchorPane right_pane) {
        try {
            // Загружаем view_calendar.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_sign_up_done.fxml"));
            AnchorPane openSearchView = loader.load();  // Загружаем AnchorPane с календарем

            // Очищаем содержимое center_pane и добавляем календарь
            right_pane.getChildren().clear();
            right_pane.getChildren().add(openSearchView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
