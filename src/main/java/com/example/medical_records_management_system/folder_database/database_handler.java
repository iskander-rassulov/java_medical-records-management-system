package com.example.medical_records_management_system.folder_database;

import java.sql.*;

public class database_handler {

    // Метод для установки соединения с базой данных
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Загружаем драйвер PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Устанавливаем соединение с базой данных
            connection = DriverManager.getConnection(
                    database_configuration.getUrl(),
                    database_configuration.getUsername(),
                    database_configuration.getPassword()
            );

            System.out.println("Соединение с базой данных успешно установлено!");

        } catch (SQLException e) {
            System.out.println("Ошибка соединения с базой данных.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не найден.");
            e.printStackTrace();
        }
        return connection;
    }

    // Новый метод для получения URL изображения по doctor_id
    public String getDoctorImageUrl(int doctorId) {
        String query = "SELECT image_icon FROM doctors WHERE doctor_id = ?";
        String imageUrl = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                imageUrl = resultSet.getString("image_icon").trim();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return imageUrl;
    }
}

