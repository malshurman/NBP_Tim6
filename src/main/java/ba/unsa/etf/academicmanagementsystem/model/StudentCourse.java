package ba.unsa.etf.academicmanagementsystem.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "NBP_STUDENT_COURSE")
@Data
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId id;

    @Embeddable
    @Data
    public static class StudentCourseId {
        @NotNull
        @ManyToOne
        @JoinColumn(name = "student_id", nullable = false)
        private User student;

        @NotNull
        @ManyToOne
        @JoinColumn(name = "course_id", nullable = false)
        private Course course;
    }
}