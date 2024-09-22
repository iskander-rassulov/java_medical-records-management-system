package com.example.medical_records_management_system;

import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Objects;

public class func_user_profile {

    private final database_handler databaseHandler = new database_handler();

    // Метод для обновления профиля на основе данных доктора
    public void updateProfile(data_doctor doctorData, Text fullName, ImageView imageIcon, Text text_speciality) {
        // Установка полного имени
        String textName = ("Dr. " + doctorData.getFirstName().trim() + " " + doctorData.getLastName().trim());
        fullName.setText(textName);
        System.out.println(textName);

        // Установка специальности
        String speciality = doctorData.getSpeciality().trim();
        text_speciality.setText(speciality);

        displayUserProfileImage(doctorData.getDoctorId(), imageIcon);
    }

    // Метод для отображения изображения профиля пользователя
    public void displayUserProfileImage(int doctorId, ImageView imageView) {
        // Получаем URL изображения из базы данных
        String imageUrl = databaseHandler.getDoctorImageUrl(doctorId);

        if (imageUrl != null && !imageUrl.isEmpty()) {
            try {
                // Загружаем изображение по URL
                Image image = new Image(imageUrl);
                imageView.setImage(image);
                imageView.setFitWidth(125);   // Установи нужную ширину
                imageView.setFitHeight(125);  // Установи нужную высоту
                imageView.setPreserveRatio(false);  // Отключаем сохранение пропорций
                imageView.setSmooth(true);  // Плавное масштабирование

            } catch (Exception e) {
                System.out.println("Не удалось загрузить изображение: " + e.getMessage());
            }
        } else {
            System.out.println("URL изображения не найден или пустой.");
        }
    }

}
