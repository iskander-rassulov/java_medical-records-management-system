package com.example.medical_records_management_system.folder_make_record;

import com.example.medical_records_management_system.folder_data.data_doctor;
import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_login_page.func_check_login_data;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;
import java.time.LocalDate;

public class func_make_record {
    @FXML
    public TextField valFirstName;

    @FXML
    public TextField valSecondName;
    @FXML
    public TextField valGender;
    @FXML
    public TextField valPhoneNumber;
    @FXML
    public TextField valEmail;
    @FXML
    public TextField valAddress;

    @FXML
    public TextField valDiagnosis;
    @FXML
    public TextArea valTreatmentPlan;
    @FXML
    public JFXButton buttonSave;
    @FXML
    public Text text_record_created;
    @FXML
    public DatePicker valVisitDate;
    @FXML
    public DatePicker valDateOfBirth;

    @FXML
    public void initialize() {
        buttonSave.setOnAction(event -> {
            // Считать данные пользователя
            String firstName = valFirstName.getText();
            String secondName = valSecondName.getText();
            LocalDate dateOfBirth = valDateOfBirth.getValue();
            String gender = valGender.getText();
            String phoneNumber = valPhoneNumber.getText();
            String email = valEmail.getText();
            String address = valAddress.getText();
            LocalDate visitDate = valVisitDate.getValue();
            String diagnosis = valDiagnosis.getText();
            String treatmentPlan = valTreatmentPlan.getText();
            text_record_created.setVisible(true);
            // Вызов функции для создания записи
            createRecord(firstName, secondName, diagnosis, treatmentPlan, visitDate, dateOfBirth, gender, phoneNumber, email, address);
        });
    }

    public void createRecord(String firstName, String lastName, String diagnosis, String treatmentPlan,
                             LocalDate visitDate, LocalDate dateOfBirth, String gender, String phoneNumber,
                             String email, String address) {
        // Получаем данные доктора
        data_doctor doctor = func_check_login_data.getDoctorData();
        int doctorId = doctor.getDoctorId();

        // Преобразуем строковые даты в формат java.sql.Date
        Date dateOfBirthSql = Date.valueOf(dateOfBirth); // Преобразование даты рождения
        Date visitDateSql = Date.valueOf(visitDate); // Преобразование даты визита

        // Генерируем уникальные patient_id и medical_record_id
        int patientId = generateUniqueId("patients", "patient_id");
        int medicalRecordId = generateUniqueId("medical_records", "medical_record_id");

        // Вставляем нового пациента
        insertNewPatient(patientId, firstName, lastName, dateOfBirthSql, gender, phoneNumber, email, address);

        // Вставляем новую медицинскую запись
        insertNewMedicalRecord(medicalRecordId, patientId, doctorId, diagnosis, treatmentPlan, visitDateSql);

        updatePatientRecordId(patientId, medicalRecordId);
    }



    // Метод для генерации уникального ID
    private int generateUniqueId(String tableName, String columnName) {
        int newId = -1;
        try (Connection connection = database_handler.getConnection()) {
            String query = "SELECT MAX(" + columnName + ") AS max_id FROM " + tableName;
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                newId = resultSet.getInt("max_id") + 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newId == -1 ? 1 : newId; // если записей нет, начинаем с 1
    }

    // Метод для добавления нового пациента
    private void insertNewPatient(int patientId, String firstName, String lastName, Date dateOfBirth,
                                  String gender, String phoneNumber, String email, String address) {
        String query = "INSERT INTO patients (patient_id, first_name, last_name, date_of_birth, gender, phone_number, email, address) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = database_handler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, patientId);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setDate(4, dateOfBirth); // Использование java.sql.Date для даты рождения
            statement.setString(5, gender);
            statement.setString(6, phoneNumber);
            statement.setString(7, email);
            statement.setString(8, address);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Метод для добавления новой медицинской записи
    private void insertNewMedicalRecord(int medicalRecordId, int patientId, int doctorId, String diagnosis,
                                        String treatmentPlan, Date visitDate) {
        String query = "INSERT INTO medical_records (medical_record_id, patient_id, doctor_id, diagnosis, treatment_plan, visit_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = database_handler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medicalRecordId);
            statement.setInt(2, patientId);
            statement.setInt(3, doctorId);
            statement.setString(4, diagnosis);
            statement.setString(5, treatmentPlan);
            statement.setDate(6, visitDate); // Использование java.sql.Date для даты визита
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updatePatientRecordId(int patientId, int medicalRecordId) {
        String query = "UPDATE patients SET medical_record_id = ? WHERE patient_id = ?";
        try (Connection connection = database_handler.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, medicalRecordId);
            statement.setInt(2, patientId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
