package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.ExamRegistration;
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
public class ExamRegistrationRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<ExamRegistration> findAllExamRegistrations() {
        String sql = "SELECT * FROM NBP_EXAM_REGISTRATION";
        return jdbcTemplate.query(sql, new ExamRegistrationMapper());
    }

    public ExamRegistration findById(Long id) {
        String sql = "SELECT * FROM NBP_EXAM_REGISTRATION WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new ExamRegistrationMapper(), id);
    }

    public List<ExamRegistration> findByStudentId(Long studentId) {
        String sql = "SELECT * FROM NBP_EXAM_REGISTRATION WHERE STUDENT_ID = ?";
        return jdbcTemplate.query(sql, new ExamRegistrationMapper(), studentId);
    }

    @Transactional
    public ExamRegistration save(ExamRegistration examRegistration) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (examRegistration.getId() == null) {
            String sql = "INSERT INTO NBP_EXAM_REGISTRATION (REGISTRATION_DATE, EXAM_ID, STUDENT_ID) VALUES (?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(examRegistration.getRegistrationDate()));
                ps.setLong(2, examRegistration.getExamId());
                ps.setLong(3, examRegistration.getStudentId());
                return ps;
            }, keyHolder);
            examRegistration.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_EXAM_REGISTRATION SET REGISTRATION_DATE = ?, EXAM_ID = ?, STUDENT_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    examRegistration.getRegistrationDate(),
                    examRegistration.getExamId(),
                    examRegistration.getStudentId(),
                    examRegistration.getId());
        }
        return examRegistration;
    }

    @Transactional
    public void deleteExamRegistrationById(Long id) {
        String sql = "DELETE FROM NBP_EXAM_REGISTRATION WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class ExamRegistrationMapper implements RowMapper<ExamRegistration> {
        @Override
        public ExamRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
            ExamRegistration examRegistration = new ExamRegistration();
            examRegistration.setId(rs.getLong("ID"));
            examRegistration.setRegistrationDate(rs.getTimestamp("REGISTRATION_DATE").toLocalDateTime());
            examRegistration.setExamId(rs.getLong("EXAM_ID"));
            examRegistration.setStudentId(rs.getLong("STUDENT_ID"));
            return examRegistration;
        }
    }
}