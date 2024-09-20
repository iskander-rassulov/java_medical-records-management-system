package com.example.medical_records_management_system.folder_database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database_handler {

    // Метод для установки соединения с базой данных
    Connection getConnection() {
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


}

