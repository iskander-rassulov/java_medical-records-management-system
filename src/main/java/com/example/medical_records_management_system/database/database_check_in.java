package com.example.medical_records_management_system.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database_check_in {

    public void displayAllDoctors() {
        database_handler dbHandler = new database_handler();
        String query = "SELECT * FROM doctors";

        try (Connection connection = dbHandler.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.printf("%-5s %-15s %-15s %-15s %-10s %-15s %-20s%n",
                    "ID", "First Name", "Last Name", "Username", "Password", "Icon", "Speciality");
            System.out.println("------------------------------------------------------------------------------------------");

            while (resultSet.next()) {
                int doctorId = resultSet.getInt("doctor_id");
                String firstName = resultSet.getString("first_name").trim();
                String lastName = resultSet.getString("last_name").trim();
                String username = resultSet.getString("username").trim();
                String password = resultSet.getString("password").trim();
                String imageIcon = resultSet.getString("image_icon").trim();
                String speciality = resultSet.getString("speciality").trim();

                System.out.printf("%-5d %-15s %-15s %-15s %-10s %-15s %-20s%n",
                        doctorId, firstName, lastName, username, password, imageIcon, speciality);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
