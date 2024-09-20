package com.example.medical_records_management_system.folder_login_page;

import com.example.medical_records_management_system.folder_database.database_handler;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class func_check_login_data {

    // Получаем доступ к полям через контроллер
    public void checkLoginData(TextField field_username, PasswordField field_password) {
        // Считываем введенные данные
        String username = field_username.getText();
        String password = field_password.getText();

        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Поля не должны быть пустыми");
            return;
        }

        // Проверяем данные с базой данных
        if (checkUserInDatabase(username, password)) {
            System.out.println("Пользователь найден");
        } else {
            System.out.println("Пользователь не найден");
        }

    }

    // Метод для проверки пользователя в базе данных
    private boolean checkUserInDatabase(String username, String password) {
        boolean userExists = false;
        String query = "SELECT * FROM doctors WHERE username = ? AND password = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Пользователь найден
                userExists = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }
}
