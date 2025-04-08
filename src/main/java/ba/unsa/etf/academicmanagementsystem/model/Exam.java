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
@Table(name = "NBP_EXAM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "EXAM_DATE", nullable = false)
    @NotNull(message = "Exam date cannot be null")
    private LocalDateTime examDate;

    @Column(name = "COURSE_ID", nullable = false)
    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @Column(name = "ROOM_ID", nullable = false)
    @NotNull(message = "Room ID cannot be null")
    private Long roomId;
}