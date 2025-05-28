package ba.unsa.etf.academicmanagementsystem.notifications.grading.repository;

import ba.unsa.etf.academicmanagementsystem.notifications.grading.model.GradingReminder;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GradingReminderRepository {
    private final JdbcTemplate jdbcTemplate;

    private static class GradingReminderMapper implements RowMapper<GradingReminder> {
        @Override
        public GradingReminder mapRow(ResultSet rs, int rowNum) throws SQLException {
            GradingReminder reminder = new GradingReminder();
            reminder.setExamId(rs.getLong("exam_id"));
            Timestamp ts = rs.getTimestamp("exam_date");
            reminder.setExamDate(ts != null ? ts.toLocalDateTime() : null);
            reminder.setCourseName(rs.getString("course_name"));
            reminder.setProfessorEmail(rs.getString("professor_email"));
            reminder.setFirstName(rs.getString("first_name"));
            reminder.setLastName(rs.getString("last_name"));
            return reminder;
        }
    }

    public List<GradingReminder> findPendingReminders() {
        String sql = "SELECT exam_id, exam_date, course_name, professor_email, first_name, last_name FROM V_PENDING_GRADING_REMINDERS";
        return jdbcTemplate.query(sql, new GradingReminderMapper());
    }
}