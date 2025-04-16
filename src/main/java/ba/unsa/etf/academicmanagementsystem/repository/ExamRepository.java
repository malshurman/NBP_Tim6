package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Exam;
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
public class ExamRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Exam> findAllExams() {
        String sql = "SELECT * FROM NBP_EXAM";
        return jdbcTemplate.query(sql, new ExamMapper());
    }

    public Exam findById(Long id) {
        String sql = "SELECT * FROM NBP_EXAM WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new ExamMapper(), id);
    }

    public List<Exam> findByCourseId(Long courseId) {
        String sql = "SELECT * FROM NBP_EXAM WHERE COURSE_ID = ?";
        return jdbcTemplate.query(sql, new ExamMapper(), courseId);
    }

    @Transactional
    public Exam save(Exam exam) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (exam.getId() == null) {
            String sql = "INSERT INTO NBP_EXAM (EXAM_DATE, COURSE_ID, ROOM_ID) VALUES (?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(exam.getExamDate()));
                ps.setLong(2, exam.getCourseId());
                ps.setLong(3, exam.getRoomId());
                return ps;
            }, keyHolder);
            exam.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_EXAM SET EXAM_DATE = ?, COURSE_ID = ?, ROOM_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    exam.getExamDate(),
                    exam.getCourseId(),
                    exam.getRoomId(),
                    exam.getId());
        }
        return exam;
    }

    @Transactional
    public void deleteExamById(Long id) {
        String sql = "DELETE FROM NBP_EXAM WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class ExamMapper implements RowMapper<Exam> {

        @Override
        public Exam mapRow(ResultSet rs, int rowNum) throws SQLException {
            Exam exam = new Exam();
            exam.setId(rs.getLong("ID"));
            exam.setExamDate(rs.getTimestamp("EXAM_DATE").toLocalDateTime());
            exam.setCourseId(rs.getLong("COURSE_ID"));
            exam.setRoomId(rs.getLong("ROOM_ID"));
            return exam;
        }
    }
}