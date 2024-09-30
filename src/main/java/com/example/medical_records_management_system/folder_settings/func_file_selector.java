package com.example.medical_records_management_system.folder_settings;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class func_file_selector {
    public static String chooseImage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбрать изображение");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            return file.toURI().toString();  // Возвращаем URL файла
        }
        return null;
    }
}
