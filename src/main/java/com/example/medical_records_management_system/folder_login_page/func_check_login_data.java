package com.example.medical_records_management_system.folder_login_page;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.folder_database.database_handler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class func_check_login_data {

    private static final data_doctor doctorData = new data_doctor(); // Экземпляр класса для хранения данных

    // Получаем доступ к полям через контроллер
    public static boolean checkLoginData(String username, String password) {

        // Проверка на пустые поля
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }

        // Проверяем данные с базой данных
        boolean userExists = checkUserInDatabase(username, password);
        if (userExists) {
            System.out.println("Пользователь найден");
            System.out.println("Данные доктора: ");
            System.out.println("ID: " + doctorData.getDoctorId());
            System.out.println("Имя: " + doctorData.getFirstName());
            System.out.println("Фамилия: " + doctorData.getLastName());
            System.out.println("Логин: " + doctorData.getUsername());
            System.out.println("Пароль: " + doctorData.getPassword());
            System.out.println("Иконка: " + doctorData.getImageIcon());
            System.out.println("Специальность: " + doctorData.getSpeciality());
        } else {
            System.out.println("Пользователь не найден");
        }

        // Проверяем данные с базой данных
        return checkUserInDatabase(username, password);
    }

    // Метод для проверки пользователя в базе данных
    private static boolean checkUserInDatabase(String username, String password) {
        boolean userExists = false;
        String query = "SELECT * FROM doctors WHERE username = ? AND password = ?";

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Пользователь найден, сохраняем данные в doctorData
                userExists = true;
                doctorData.setDoctorId(resultSet.getInt("doctor_id"));
                doctorData.setFirstName(resultSet.getString("first_name"));
                doctorData.setLastName(resultSet.getString("last_name"));
                doctorData.setUsername(resultSet.getString("username"));
                doctorData.setPassword(resultSet.getString("password"));
                doctorData.setImageIcon(resultSet.getString("image_icon"));
                doctorData.setSpeciality(resultSet.getString("speciality"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userExists;
    }

    // Метод для проверки сохраненных данных
    public static boolean isLoggedIn() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user_data.txt"))) {
            String line;
            String username = "";
            String password = "";
            boolean keepLoggedIn = false;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("username=")) {
                    username = line.substring("username=".length());
                } else if (line.startsWith("password=")) {
                    password = line.substring("password=".length());
                } else if (line.startsWith("keep_logged_in=")) {
                    keepLoggedIn = Boolean.parseBoolean(line.substring("keep_logged_in=".length()));
                }
            }

            if (keepLoggedIn && checkLoginData(username, password)) {
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static data_doctor getDoctorData() {
        return doctorData;
    }
}
