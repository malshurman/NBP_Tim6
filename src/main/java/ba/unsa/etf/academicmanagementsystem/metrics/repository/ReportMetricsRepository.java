package ba.unsa.etf.academicmanagementsystem.metrics.repository;

import ba.unsa.etf.academicmanagementsystem.metrics.dto.StudentCourseMetricDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReportMetricsRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<StudentCourseMetricDto> getStudentCourseMetrics() {
        String sql = "SELECT * FROM V_STUDENT_COURSE_METRICS";
        return jdbcTemplate.query(sql, (rs, rowNum) -> StudentCourseMetricDto.builder()
                .studentId(rs.getLong("student_id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .courseId(rs.getLong("course_id"))
                .courseName(rs.getString("course_name"))
                .credits(rs.getInt("credits"))
                .numGrades(rs.getInt("num_grades"))
                .totalGrade(rs.getDouble("total_grade"))
                .avgGrade(rs.getDouble("avg_grade"))
                .minGrade(rs.getDouble("min_grade"))
                .maxGrade(rs.getDouble("max_grade"))
                .build());
    }
}
