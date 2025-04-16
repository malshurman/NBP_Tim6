package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class GradeRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Grade> findAllGrades() {
        String sql = "SELECT * FROM NBP_GRADE";
        return jdbcTemplate.query(sql, new GradeMapper());
    }

    public Grade findGradeById(Long id) {
        String sql = "SELECT * FROM NBP_GRADE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new GradeMapper(), id);
    }

    public List<Grade> findByStudentId(Long studentId) {
        String sql = "SELECT * FROM NBP_GRADE WHERE STUDENT_ID = ?";
        return jdbcTemplate.query(sql, new GradeMapper(), studentId);
    }

    @Transactional
    public Grade save(Grade grade) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (grade.getId() == null) {
            String sql = "INSERT INTO NBP_GRADE (DATE_ASSIGNED, GRADE, EXAM_ID, STUDENT_ID) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(grade.getDateAssigned()));
                ps.setInt(2, grade.getGrade());
                ps.setLong(3, grade.getExamId());
                ps.setLong(4, grade.getStudentId());
                return ps;
            }, keyHolder);
            grade.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_GRADE SET DATE_ASSIGNED = ?, GRADE = ?, EXAM_ID = ?, STUDENT_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    grade.getDateAssigned(),
                    grade.getGrade(),
                    grade.getExamId(),
                    grade.getStudentId(),
                    grade.getId());
        }
        return grade;
    }

    @Transactional
    public void deleteGradeById(Long id) {
        String sql = "DELETE FROM NBP_GRADE WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class GradeMapper implements RowMapper<Grade> {

        @Override
        public Grade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Grade grade = new Grade();
            grade.setId(rs.getLong("ID"));
            grade.setDateAssigned(rs.getTimestamp("DATE_ASSIGNED").toLocalDateTime());
            grade.setGrade(rs.getInt("GRADE"));
            grade.setExamId(rs.getLong("EXAM_ID"));
            grade.setStudentId(rs.getLong("STUDENT_ID"));
            return grade;
        }
    }
}