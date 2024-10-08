package com.example.medical_records_management_system;

import com.example.medical_records_management_system.folder_database.database_check_in;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    database_check_in databaseCheckIn = new database_check_in();

    @Override
    public void start(Stage primaryStage) {
        try {
            // Загрузка FXML файла
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/medical_records_management_system/view_login_page.fxml")));

            // Установка сцены
            Scene scene = new Scene(root);

            // Установка заголовка и сцены
            primaryStage.setResizable(false);
            primaryStage.setTitle("Medical Records Management System");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        databaseCheckIn.displayAllDoctors();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
