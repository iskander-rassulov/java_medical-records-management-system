package com.example.medical_records_management_system.folder_settings;

import com.example.medical_records_management_system.AppContext;
import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.example.medical_records_management_system.folder_main.controller_main;
import com.example.medical_records_management_system.folder_main.func_user_profile;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class func_settings {
    @FXML
    public Text text_changes_saved;
    @FXML
    public Text text_old_password;
    @FXML
    private ImageView valImageView;
    @FXML
    private TextField valUsername;  // Поле для нового username
    @FXML
    private TextField valOldPassword;
    @FXML
    private TextField valNewPassword;
    @FXML
    private JFXButton buttonUploadFile;
    @FXML
    private JFXButton buttonSave;

    private String imageUrl;

    public void initialize() {
        // Фиксируем размеры ImageView
        valImageView.setFitHeight(150);
        valImageView.setFitWidth(150);

        // Загрузка файла изображения
        buttonUploadFile.setOnMouseClicked(event -> {
            Stage stage = (Stage) valImageView.getScene().getWindow();
            imageUrl = func_file_selector.chooseImage(stage);  // Открываем FileChooser и получаем URL
            if (imageUrl != null) {
                valImageView.setImage(new Image(imageUrl));  // Отображаем изображение
            }
        });

        // Обработка нажатия на кнопку Save
        buttonSave.setOnMouseClicked(event -> {
            String oldPassword = valOldPassword.getText();
            String newPassword = valNewPassword.getText();
            String newUsername = valUsername.getText();  // Получаем новое имя пользователя
            text_changes_saved.setVisible(true);

            // Проверка изменения пароля
            if (!oldPassword.isEmpty() && !newPassword.isEmpty()) {
                if (checkOldPassword(oldPassword)) {
                    updatePassword(newPassword);
                    text_old_password.setVisible(false);
                } else {
                    text_old_password.setVisible(true);
                    System.out.println("Старый пароль неверен.");
                }
            }

            // Обновляем изображение, если загружено
            if (imageUrl != null) {
                updateDoctorImage(imageUrl);
                data_doctor dataDoctor = func_check_login_data.getDoctorData();
                func_user_profile userProfile = new func_user_profile();
                userProfile.updateProfile(dataDoctor, AppContext.getInstance().getFullName(), AppContext.getInstance().getImageIcon(), AppContext.getInstance().getSpeciality());
            }

            // Обновляем имя пользователя, если указано новое
            if (!newUsername.isEmpty()) {
                updateUsername(newUsername);
            }
        });
    }

    // Проверяем старый пароль
    private boolean checkOldPassword(String oldPassword) {
        String currentPassword = data_doctor.getPassword().trim();// Текущий пароль доктора
        System.out.println(currentPassword);
        return currentPassword.equals(oldPassword);
    }

    // Обновляем пароль
    private void updatePassword(String newPassword) {
        int doctorId = data_doctor.getDoctorId();
        String updateSQL = "UPDATE doctors SET password = ? WHERE doctor_id = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newPassword);
            preparedStatement.setInt(2, doctorId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Пароль успешно обновлен.");
            } else {
                System.out.println("Не удалось обновить пароль.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Обновляем изображение доктора
    private void updateDoctorImage(String imageUrl) {
        int doctorId = data_doctor.getDoctorId();
        String updateSQL = "UPDATE doctors SET image_icon = ? WHERE doctor_id = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, imageUrl);
            preparedStatement.setInt(2, doctorId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Изображение успешно обновлено.");
            } else {
                System.out.println("Не удалось обновить изображение.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Обновляем имя пользователя
    private void updateUsername(String newUsername) {
        int doctorId = data_doctor.getDoctorId();
        String updateSQL = "UPDATE doctors SET username = ? WHERE doctor_id = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, newUsername);
            preparedStatement.setInt(2, doctorId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Имя пользователя успешно обновлено.");
            } else {
                System.out.println("Не удалось обновить имя пользователя.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}