package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_GRADE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DATE_ASSIGNED", nullable = false)
    @NotNull(message = "Date assigned cannot be null")
    private LocalDateTime dateAssigned;

    @Column(name = "GRADE", nullable = false)
    @NotNull(message = "Grade cannot be null")
    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 5, message = "Grade must be at most 5")
    private Integer grade;

    @Column(name = "EXAM_ID", nullable = false)
    @NotNull(message = "Exam ID cannot be null")
    private Long examId;

    @Column(name = "STUDENT_ID", nullable = false)
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;
}