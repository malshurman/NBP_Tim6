package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRegistration {

    private Long id;

    @NotNull(message = "Registration date cannot be null")
    private LocalDateTime registrationDate;

    @NotNull(message = "Exam ID cannot be null")
    private Long examId;

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;
}