package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Exam;
import ba.unsa.etf.academicmanagementsystem.model.User;
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
import java.util.List;
import java.util.Map;

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
        if (exam.getId() == null) {
            String sql = "INSERT INTO NBP_EXAM (EXAM_DATE, COURSE_ID, ROOM_ID, EXAM_PDF) VALUES (?, ?, ?, ?)";


            // Specify column names for generated keys - this is important for Oracle
            String[] returnColumns = {"ID"};
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, returnColumns);
                ps.setTimestamp(1, java.sql.Timestamp.valueOf(exam.getExamDate()));
                ps.setLong(2, exam.getCourseId());
                ps.setLong(3, exam.getRoomId());
                ps.setBytes(4, exam.getExamPdf());
                return ps;
            }, keyHolder);

            // Try multiple approaches to get the generated ID
            Long generatedId = null;

            // Try with different key name variations (Oracle can be case-sensitive)
            if (keyHolder.getKeys() != null) {
                Object key = keyHolder.getKeys().get("ID");
                if (key == null) key = keyHolder.getKeys().get("id");
                if (key instanceof Number) {
                    generatedId = ((Number) key).longValue();
                }
            }

            // Try with keyHolder.getKey() if above failed
            if (generatedId == null && keyHolder.getKey() != null) {
                generatedId = keyHolder.getKey().longValue();
            }

            if (generatedId != null) {
                exam.setId(generatedId);
            } else {
                throw new RuntimeException("Failed to retrieve generated ID for user");
            }
        } else {
            String sql = "UPDATE NBP_EXAM SET EXAM_DATE = ?, COURSE_ID = ?, ROOM_ID = ?, EXAM_PDF = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    java.sql.Timestamp.valueOf(exam.getExamDate()),
                    exam.getCourseId(),
                    exam.getRoomId(),
                    exam.getExamPdf(),
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
            return Exam.builder()
                    .id(rs.getLong("ID"))
                    .examDate(rs.getTimestamp("EXAM_DATE").toLocalDateTime())
                    .courseId(rs.getLong("COURSE_ID"))
                    .roomId(rs.getLong("ROOM_ID"))
                    .examPdf(rs.getBytes("EXAM_PDF"))
                    .build();
        }
    }
}