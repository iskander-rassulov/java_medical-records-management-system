package com.example.medical_records_management_system;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.util.Objects;

public class func_user_profile {

    // Метод для обновления профиля на основе данных доктора
    public void updateProfile(data_doctor doctorData, Text fullName, ImageView image_icon, Text text_speciality) {
        // Установка полного имени
        String textName = ("Dr. " + doctorData.getFirstName().trim() + " " + doctorData.getLastName().trim());
        fullName.setText(textName);
        System.out.println(textName);

        // Установка изображения icon_1.jpg
        Image image = new Image(Objects.requireNonNull(getClass().getResource("/com/example/medical_records_management_system/image/icon_1.jpg")).toExternalForm());
        image_icon.setImage(image);

        // Установка специальности
        String speciality = doctorData.getSpeciality().trim();
        text_speciality.setText(speciality);
    }
}
