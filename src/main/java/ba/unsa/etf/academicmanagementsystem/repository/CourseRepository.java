package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Course;
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
public class CourseRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Course> findAll() {
        String sql = "SELECT * FROM NBP_COURSE";
        return jdbcTemplate.query(sql, new CourseMapper());
    }

    public Course findByCode(String code) {
        String sql = "SELECT * FROM NBP_COURSE WHERE CODE = ?";
        return jdbcTemplate.queryForObject(sql, new CourseMapper(), code);
    }

    public Course findById(Long id) {
        String sql = "SELECT * FROM NBP_COURSE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new CourseMapper(), id);
    }

    @Transactional
    public Course save(Course course) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (course.getId() == null) {
            String sql = "INSERT INTO NBP_COURSE (CODE, CREDITS, NAME, PROFESSOR_ID, SEMESTER_ID) VALUES (?, ?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, course.getCode());
                ps.setLong(2, course.getCredits());
                ps.setString(3, course.getName());
                ps.setLong(4, course.getProfessorId());
                ps.setLong(5, course.getSemesterId());
                return ps;
            }, keyHolder);
            course.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_COURSE SET CODE = ?, CREDITS = ?, NAME = ?, PROFESSOR_ID = ?, SEMESTER_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    course.getCode(),
                    course.getCredits(),
                    course.getName(),
                    course.getProfessorId(),
                    course.getSemesterId(),
                    course.getId());
        }
        return course;
    }

    @Transactional
    public void delete(Long id) {
        String sql = "DELETE FROM NBP_COURSE WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class CourseMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
            Course course = new Course();
            course.setId(rs.getLong("ID"));
            course.setCode(rs.getString("CODE"));
            course.setCredits(rs.getLong("CREDITS"));
            course.setName(rs.getString("NAME"));
            course.setProfessorId(rs.getLong("PROFESSOR_ID"));
            course.setSemesterId(rs.getLong("SEMESTER_ID"));
            return course;
        }
    }
}