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
public class Exam {

    private Long id;

    @NotNull(message = "Exam date cannot be null")
    private LocalDateTime examDate;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;
}