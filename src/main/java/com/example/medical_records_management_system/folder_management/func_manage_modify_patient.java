package com.example.medical_records_management_system.folder_management;

import com.example.medical_records_management_system.folder_database.database_handler;
import com.example.medical_records_management_system.folder_patient.func_table_of_patients;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class func_manage_modify_patient {
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
    public JFXButton buttonSave;
    @FXML
    public Text text_modified_patients;
    @FXML
    public DatePicker valDateOfBirth;


    public void initialize() {
        buttonSave.setOnMouseClicked(event -> {
            int chosenPatientId = func_table_of_patients.getChosenPatientId();

            // Получаем данные из текстовых полей
            String firstName = valFirstName.getText();
            String secondName = valSecondName.getText();
            LocalDate dateOfBirthStr = valDateOfBirth.getValue();
//            String dateOfBirthStr = valDateOfBirth.getText();
            String gender = valGender.getText();
            String phoneNumber = valPhoneNumber.getText();
            String email = valEmail.getText();
            String address = valAddress.getText();

            // Проверяем, что идентификатор выбранного пациента не равен 0
            if (chosenPatientId != 0) {
                updatePatient(chosenPatientId, firstName, secondName, dateOfBirthStr, gender, phoneNumber, email, address);
                text_modified_patients.setVisible(true);  // Показываем текст после успешного изменения
            } else {
                System.out.println("Пациент не выбран.");
            }
        });
    }

    private void updatePatient(int patientId, String firstName, String secondName, LocalDate dateOfBirthStr, String gender, String phoneNumber, String email, String address) {
        StringBuilder updateSQL = new StringBuilder("UPDATE patients SET ");
        boolean firstField = true;

        if (firstName != null && !firstName.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("first_name = ?");
            firstField = false;
        }
        if (secondName != null && !secondName.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("second_name = ?");
            firstField = false;
        }
        if (dateOfBirthStr != null) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("date_of_birth = ?");
            firstField = false;
        }
        if (gender != null && !gender.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("gender = ?");
            firstField = false;
        }
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("phone_number = ?");
            firstField = false;
        }
        if (email != null && !email.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("email = ?");
            firstField = false;
        }
        if (address != null && !address.isEmpty()) {
            if (!firstField) updateSQL.append(", ");
            updateSQL.append("address = ?");
        }

        updateSQL.append(" WHERE patient_id = ?");

        try (Connection connection = database_handler.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSQL.toString())) {

            int paramIndex = 1;

            if (firstName != null && !firstName.isEmpty()) {
                preparedStatement.setString(paramIndex++, firstName);
            }
            if (secondName != null && !secondName.isEmpty()) {
                preparedStatement.setString(paramIndex++, secondName);
            }
            if (dateOfBirthStr != null) {
                preparedStatement.setDate(paramIndex++, java.sql.Date.valueOf(dateOfBirthStr)); // Преобразуем LocalDate в SQL Date
            }
            if (gender != null && !gender.isEmpty()) {
                preparedStatement.setString(paramIndex++, gender);
            }
            if (phoneNumber != null && !phoneNumber.isEmpty()) {
                preparedStatement.setString(paramIndex++, phoneNumber);
            }
            if (email != null && !email.isEmpty()) {
                preparedStatement.setString(paramIndex++, email);
            }
            if (address != null && !address.isEmpty()) {
                preparedStatement.setString(paramIndex++, address);
            }

            preparedStatement.setInt(paramIndex, patientId);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Информация о пациенте успешно обновлена.");
            } else {
                System.out.println("Не удалось обновить информацию о пациенте.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsedDate = format.parse(dateString);
        return new java.sql.Date(parsedDate.getTime());
    }


}
