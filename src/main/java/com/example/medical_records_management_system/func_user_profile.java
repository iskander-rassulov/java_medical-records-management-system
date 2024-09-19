package com.example.medical_records_management_system;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class func_user_profile {

    public void setImageIcon(ImageView image_icon){
        ImageView image_icon_code = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/medical_records_management_system/image/icon_1.jpg"))));
        image_icon.setImage(image_icon_code.getImage());
    }
}
