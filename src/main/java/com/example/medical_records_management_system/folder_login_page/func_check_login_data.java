package com.example.medical_records_management_system.folder_login_page;

import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class func_check_login_data {

    // Получаем доступ к полям через контроллер
    public void checkLoginData(TextField field_username, PasswordField field_password) {
        // Считываем введенные данные
        String username = field_username.getText();
        String password = field_password.getText();

        // Логика проверки данных пользователя
        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Поля не должны быть пустыми");
            return;
        }

        // Пример дальнейшей обработки данных
        System.out.println("Введенное имя пользователя: " + username);
        System.out.println("Введенный пароль: " + password);

        // Здесь можно добавить проверку по базе данных
    }
}
