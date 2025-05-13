package ba.unsa.etf.academicmanagementsystem.metrics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseMetricDto {
    private Long studentId;
    private String firstName;
    private String lastName;
    private Long courseId;
    private String courseName;
    private Integer credits;
    private Integer numGrades;
    private Double totalGrade;
    private Double avgGrade;
    private Double minGrade;
    private Double maxGrade;
}
