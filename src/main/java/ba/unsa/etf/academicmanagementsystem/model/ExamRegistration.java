package ba.unsa.etf.academicmanagementsystem.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;

@Entity
@Table(name = "NBP_EXAM_REGISTRATION")
@Data
public class ExamRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @NotNull
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp registrationDate;
}
