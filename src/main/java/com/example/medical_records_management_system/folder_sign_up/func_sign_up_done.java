package com.example.medical_records_management_system.folder_sign_up;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class func_sign_up_done {

    public Text text_full_name;
    public ImageView imageView;

    public void assignData(String fullName, String imageUrl) {
        text_full_name.setText(fullName); // Устанавливаем полное имя
        imageView.setImage(new Image(imageUrl)); // Устанавливаем изображение
    }
}

