package com.example.medical_records_management_system.folder_sign_up;

import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_settings.func_file_selector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import com.jfoenix.controls.JFXButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class func_sign_up {
    @FXML
    public TextField valFirstName;
    @FXML
    public TextField valSecondName;
    @FXML
    public TextField valUsername;
    @FXML
    public TextField valPassword;
    @FXML
    public ComboBox<String> valMenuItem;  // ComboBox для специальностей
    @FXML
    public JFXButton buttonSignUp;
    @FXML
    public ImageView imageView;
    @FXML
    public JFXButton buttonUploadFile;
    @FXML
    public Text text_warning;
    public AnchorPane AnchorPaneR;

    private String imageUrl; // Для хранения URL выбранного изображения

    public void initialize() {
        // Добавляем варианты специальностей в ComboBox
        valMenuItem.getItems().addAll("Neurologist", "Cardiologist", "Endocrinologist");

        // Устанавливаем обработчик для ImageView, чтобы открывать file selector
        buttonUploadFile.setOnMouseClicked(event -> openFileChooser());

        // Прячем текст предупреждения по умолчанию
        text_warning.setVisible(false);

        buttonSignUp.setOnAction(event->{
            signUpDoctor();
        });
    }

    private void openFileChooser() {
        // Открываем окно для выбора файла
        Stage stage = (Stage) imageView.getScene().getWindow();
        imageUrl = func_file_selector.chooseImage(stage);

        if (imageUrl != null) {
            // Устанавливаем выбранное изображение в ImageView
            imageView.setImage(new javafx.scene.image.Image(imageUrl));
        }
    }

    @FXML
    public void signUpDoctor() {
        // Получаем данные с полей
        String firstName = valFirstName.getText();
        String lastName = valSecondName.getText();
        String username = valUsername.getText();
        String password = valPassword.getText();
        String speciality = valMenuItem.getSelectionModel().getSelectedItem();

        // Проверяем, что все поля заполнены
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || speciality == null || imageUrl == null) {
            text_warning.setVisible(true); // Показываем предупреждение
            return;
        }

        // Прячем предупреждение, если все поля заполнены
        text_warning.setVisible(false);

        // Генерируем уникальный doctor_id
        int doctorId = generateUniqueDoctorId();

        // SQL-запрос для вставки нового доктора в базу данных
        String insertQuery = "INSERT INTO doctors (doctor_id, first_name, last_name, username, password, image_icon, speciality) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1, doctorId);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, lastName);
            preparedStatement.setString(4, username);
            preparedStatement.setString(5, password);
            preparedStatement.setString(6, imageUrl); // Используем URL выбранного изображения
            preparedStatement.setString(7, speciality);

            // Выполняем запрос
            preparedStatement.executeUpdate();
            System.out.println("Доктор успешно зарегистрирован с ID: " + doctorId);

            // Переходим на view_sign_up_done.fxml и передаем данные
            openSignUpDoneView(firstName.trim() + " " + lastName.trim(), imageUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для генерации уникального doctor_id
    private int generateUniqueDoctorId() {
        Random random = new Random();
        int doctorId;
        boolean isUnique;

        do {
            doctorId = random.nextInt(10000); // Генерируем ID от 0 до 9999
            isUnique = checkDoctorIdUnique(doctorId);
        } while (!isUnique);

        return doctorId;
    }

    // Метод для проверки уникальности doctor_id
    private boolean checkDoctorIdUnique(int doctorId) {
        String checkQuery = "SELECT COUNT(*) FROM doctors WHERE doctor_id = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(checkQuery)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1) == 0; // Возвращаем true, если ID уникален
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Метод для открытия view_sign_up_done.fxml и передачи данных
    // Метод для отображения view_sign_up_done.fxml внутри того же AnchorPane
    private void openSignUpDoneView(String fullName, String imageUrl) {
        try {
            // Загружаем view_sign_up_done.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/medical_records_management_system/view_sign_up_done.fxml"));
            AnchorPane signUpDonePane = loader.load();

            // Получаем контроллер для view_sign_up_done
            func_sign_up_done controller = loader.getController();

            // Передаем данные
            controller.assignData(fullName, imageUrl);

            // Очищаем текущее содержимое AnchorPane и добавляем новое
            AnchorPaneR.getChildren().clear();
            AnchorPaneR.getChildren().add(signUpDonePane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
