package com.example.medical_records_management_system.folder_calendar;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

public class func_calendar {

    public AnchorPane generateCalendar() {
        AnchorPane calendarPane = new AnchorPane();
        DatePicker datePicker = new DatePicker();

        // Настройка компонента календаря
        datePicker.setShowWeekNumbers(true);

        // Добавляем календарь в панель
        calendarPane.getChildren().add(datePicker);

        return calendarPane;
    }
}

