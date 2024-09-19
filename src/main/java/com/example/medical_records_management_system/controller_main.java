package com.example.medical_records_management_system;

import com.jfoenix.controls.JFXButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.util.Objects;

public class controller_main {
    public AnchorPane bottom_pane;
    public AnchorPane center_pane;
    public AnchorPane left_pane;
    public ImageView image_icon;
    public Text text_full_name;
    public JFXButton button_search_records;
    public JFXButton button_patient_card;
    public JFXButton button_calendar;
    public JFXButton button_manage_records;

    public controller_main(){

    }

    public void initialize(){
        ImageView image_icon_code = new ImageView(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/com/example/medical_records_management_system/image/icon_1.jpg"))));
        image_icon.setImage(image_icon_code.getImage());
    }
}
