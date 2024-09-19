package com.example.medical_records_management_system.login_page;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class func_login_image {

    public void setLoginImage(ImageView image_icon){
        ImageView image_icon_code = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/medical_records_management_system/image/mercy.png"))));
        image_icon.setImage(image_icon_code.getImage());
    }
}
