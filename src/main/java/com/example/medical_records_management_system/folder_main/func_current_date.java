package com.example.medical_records_management_system.folder_main;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class func_current_date {

    public void setText_current_date(Text text_current_date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm [EEEE]", Locale.ENGLISH);

        // Таймер для обновления текста каждые 1 секунду
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            LocalDateTime now = LocalDateTime.now();
                            String formattedDate = now.format(formatter);
                            text_current_date.setText(formattedDate);
                        }
                )
        );

        // Настройка таймлайна для бесконечного повторения
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
}
