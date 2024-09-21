package com.example.medical_records_management_system.folder_login_page;


import com.example.medical_records_management_system.controller_main;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class controller_login {


    func_login_image funcLoginImage = new func_login_image();

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

    public void initialize(){
        funcLoginImage.setLoginImage(image_hospital);

        // Привязываем действие к кнопке
        button_sign_in.setOnAction(event -> {
            boolean loginSuccessful = func_check_login_data.checkLoginData(field_username, field_password);

            if (loginSuccessful) {
                // Меняем сцену на view_main.fxml
                switchToMainView();
            } else {
                // Показываем текст, если данные введены неверно
                incorrect_data.setVisible(true);
            }
        });

    }

    // Метод для изменения сцены на view_main.fxml
    private void switchToMainView() {
        try {
            // Загружаем view_main.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_main.fxml"));
            Parent root = loader.load();

            // Получаем контроллер для главного окна
            controller_main mainController = loader.getController();

            // Передаем данные о докторе в контроллер
            mainController.setDoctorData(func_check_login_data.getDoctorData());

            // Устанавливаем сцену
            Stage stage = (Stage) button_sign_in.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
