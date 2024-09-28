package com.example.medical_records_management_system.folder_database;

import com.example.medical_records_management_system.data_doctor;
import com.example.medical_records_management_system.data_medical_records;
import com.example.medical_records_management_system.data_patients;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.postgresql.jdbc.EscapedFunctions.USER;

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

    // Метод для получения записей по doctor_id
    public List<data_medical_records> getRecordsByDoctorId(int doctorId) {
        List<data_medical_records> records = new ArrayList<>();
        String query = "SELECT * FROM medical_records WHERE doctor_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data_medical_records record = new data_medical_records();
                record.setMedicalRecordId(resultSet.getInt("medical_record_id"));
                record.setPatientId(resultSet.getInt("patient_id"));
                record.setDoctorId(resultSet.getInt("doctor_id"));
                record.setVisitDate(String.valueOf(resultSet.getDate("visit_date")).trim());
                record.setDiagnosis(resultSet.getString("diagnosis").trim());
                record.setTreatmentPlan(resultSet.getString("treatment_plan").trim());
                records.add(record);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return records;
    }

    // Метод для получения записей по doctor_id
    public List<data_patients> getPatientsByDoctorId(int doctorId) {
        List<data_patients> patientsList = new ArrayList<>();

        String query = "SELECT p.patient_id, p.first_name, p.last_name, p.date_of_birth, p.gender, p.phone_number, p.email, p.address, p.medical_record_id " +
                "FROM patients p " +
                "JOIN medical_records mr ON p.patient_id = mr.patient_id " +
                "WHERE mr.doctor_id = ?";


        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                data_patients patient = new data_patients();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setFirstName(resultSet.getString("first_name").trim());
                patient.setSecondName(resultSet.getString("last_name").trim());
                patient.setDateOfBirth(resultSet.getString("date_of_birth").trim());
                patient.setGender(resultSet.getString("gender").trim());
                patient.setPhoneNumber(resultSet.getString("phone_number").trim());
                patient.setEmail(resultSet.getString("email").trim());
                patient.setAddress(resultSet.getString("address").trim());
                patient.setMedicalRecordId(resultSet.getInt("medical_record_id"));
                patientsList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientsList;
    }

    public data_patients getPatientById(int patientId) {
        data_patients patient = null;
        String query = "SELECT * FROM patients WHERE patient_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                patient = new data_patients();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setFirstName(resultSet.getString("first_name").trim());
                patient.setSecondName(resultSet.getString("last_name").trim());
                patient.setDateOfBirth(resultSet.getString("date_of_birth").trim());
                patient.setGender(resultSet.getString("gender").trim());
                patient.setPhoneNumber(resultSet.getString("phone_number").trim());
                patient.setEmail(resultSet.getString("email").trim());
                patient.setAddress(resultSet.getString("address").trim());
                patient.setMedicalRecordId(resultSet.getInt("medical_record_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patient;
    }

    public data_medical_records getMedicalRecordByPatientId(int patientId) {
        data_medical_records medicalRecord = null;
        String query = "SELECT * FROM medical_records WHERE patient_id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                medicalRecord = new data_medical_records();
                medicalRecord.setMedicalRecordId(resultSet.getInt("medical_record_id"));
                medicalRecord.setPatientId(resultSet.getInt("patient_id"));
                medicalRecord.setDoctorId(resultSet.getInt("doctor_id"));
                medicalRecord.setVisitDate(resultSet.getString("visit_date").trim());
                medicalRecord.setDiagnosis(resultSet.getString("diagnosis").trim());
                medicalRecord.setTreatmentPlan(resultSet.getString("treatment_plan").trim());

                System.out.println("Запись номер " + resultSet.getInt("medical_record_id"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicalRecord;
    }


}

