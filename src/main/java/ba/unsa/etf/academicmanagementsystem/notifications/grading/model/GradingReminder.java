package ba.unsa.etf.academicmanagementsystem.notifications.grading.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GradingReminder {
    private Long examId;
    private LocalDateTime examDate;
    private String courseName;
    private String professorEmail;
    private String firstName;
    private String lastName;
}