package com.example.medical_records_management_system.folder_login_page;


import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_logged_in.func_keep_logged_in;
import com.example.medical_records_management_system.folder_sign_up.func_open_sign_up;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import javafx.application.Platform;
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

    public void initialize() {
        funcLoginImage.setLoginImage(image_hospital);

        // Проверка на сохраненные данные
        if (func_check_login_data.isLoggedIn()) {
            // Используем Platform.runLater, чтобы отложить выполнение переключения сцены
            Platform.runLater(() -> {
                Stage stage = (Stage) button_sign_in.getScene().getWindow();
                funcOpenMainView.switchToMainView(stage);
            });
        } else {
            // Привязываем действие к кнопке входа только если не было автологина
            button_sign_in.setOnAction(event -> {
                boolean loginSuccessful = func_check_login_data.checkLoginData(field_username.getText(), field_password.getText());

                if (loginSuccessful) {
                    // Если пользователь выбрал "keep me logged in"
                    if (check_box_keep_logged_in.isSelected()) {
                        data_doctor doctor = func_check_login_data.getDoctorData(); // Получи объект с данными
                        func_keep_logged_in.saveUserData(doctor, true);
                    }
                    // Переключаем сцену
                    Stage stage = (Stage) button_sign_in.getScene().getWindow();
                    funcOpenMainView.switchToMainView(stage);
                } else {
                    incorrect_data.setVisible(true);
                }
            });

            button_sign_up.setOnAction(event->{
                func_open_sign_up funcOpenSignUp = new func_open_sign_up();
                funcOpenSignUp.showSignUp(pane_right);
            });
        }
    }


}
