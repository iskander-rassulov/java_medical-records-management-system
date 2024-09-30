package com.example.medical_records_management_system.folder_settings;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class func_settings {
    @FXML
    public ImageView valImageView;
    @FXML
    public JFXButton buttonSave;
    @FXML
    public JFXButton buttonUploadFile;
    @FXML
    public TextField valUsername;
    @FXML
    public PasswordField valOldPassword;
    @FXML
    public PasswordField valNewPassword;

    public void initialize(){
        int doctorId = data_doctor.getDoctorId();
    }

    public void uploadImage(){

    }
}
