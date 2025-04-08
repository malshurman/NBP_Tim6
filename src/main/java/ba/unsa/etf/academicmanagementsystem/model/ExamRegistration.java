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
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_EXAM_REGISTRATION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    @NotNull(message = "Registration date cannot be null")
    private LocalDateTime registrationDate;

    @Column(name = "EXAM_ID", nullable = false)
    @NotNull(message = "Exam ID cannot be null")
    private Long examId;

    @Column(name = "STUDENT_ID", nullable = false)
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;
}