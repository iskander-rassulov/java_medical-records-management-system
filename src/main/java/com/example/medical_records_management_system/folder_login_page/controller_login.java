package com.example.medical_records_management_system.folder_login_page;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class controller_login {

    func_login_image funcLoginImage = new func_login_image();
    func_open_main_view funcOpenMainView = new func_open_main_view();

    public AnchorPane pane_left;
    public Text text_sign_in;
    public TextField field_username;
    public PasswordField field_password;
    public JFXButton button_sign_in;
    public Hyperlink button_forgot_password;
    public Hyperlink button_sign_up;
    public AnchorPane pane_right;
    public ImageView image_hospital;
    public Text incorrect_data;
    public JFXCheckBox check_box_keep_logged_in;

    public void initialize(){
        funcLoginImage.setLoginImage(image_hospital);

        // Привязываем действие к кнопке
        button_sign_in.setOnAction(event -> {
            boolean loginSuccessful = func_check_login_data.checkLoginData(field_username, field_password);

            if (loginSuccessful) {
                // Меняем сцену на view_main.fxml
                Stage stage = (Stage) button_sign_in.getScene().getWindow();
                funcOpenMainView.switchToMainView(stage);
            } else {
                // Показываем текст, если данные введены неверно
                incorrect_data.setVisible(true);
            }
        });

    }

}
