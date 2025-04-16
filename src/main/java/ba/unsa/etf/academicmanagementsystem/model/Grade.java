package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
public class Grade {

    private Long id;

    @NotNull(message = "Date assigned cannot be null")
    private LocalDateTime dateAssigned;

    @NotNull(message = "Grade cannot be null")
    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 5, message = "Grade must be at most 5")
    private Integer grade;

    @NotNull(message = "Exam ID cannot be null")
    private Long examId;

    @NotNull(message = "Student ID cannot be null")
    private Long studentId;
}