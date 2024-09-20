package com.example.medical_records_management_system.folder_login_page;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class controller_login {

    func_login_image funcLoginImage = new func_login_image();
    func_check_login_data funcCheckLoginData = new func_check_login_data();

    public AnchorPane pane_left;
    public Text text_sign_in;
    public TextField field_username;
    public PasswordField field_password;
    public JFXButton button_sign_in;
    public Hyperlink button_forgot_password;
    public Hyperlink button_sign_up;
    public AnchorPane pane_right;
    public ImageView image_hospital;

    public void initialize(){
        funcLoginImage.setLoginImage(image_hospital);
        // Привязываем действие к кнопке
        button_sign_in.setOnAction(event -> {
            funcCheckLoginData.checkLoginData(field_username, field_password);
        });
    }


}
