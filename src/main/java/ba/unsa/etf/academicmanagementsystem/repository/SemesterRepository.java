package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Semester;
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
public class SemesterRepository {

    private final JdbcTemplate jdbcTemplate;

    private static final class SemesterMapper implements RowMapper<Semester> {
        @Override
        public Semester mapRow(ResultSet rs, int rowNum) throws SQLException {
            Semester semester = new Semester();
            semester.setId(rs.getLong("ID"));
            semester.setName(rs.getString("NAME"));
            semester.setStartDate(rs.getTimestamp("START_DATE").toLocalDateTime());
            semester.setEndDate(rs.getTimestamp("END_DATE").toLocalDateTime());
            return semester;
        }
    }

    public List<Semester> findAllSemesters() {
        String sql = "SELECT * FROM NBP_SEMESTER";
        return jdbcTemplate.query(sql, new SemesterMapper());
    }

    public Semester findByName(String name) {
        String sql = "SELECT * FROM NBP_SEMESTER WHERE NAME = ?";
        return jdbcTemplate.queryForObject(sql, new SemesterMapper(), name);
    }

    public Semester findSemesterById(Long id) {
        String sql = "SELECT * FROM NBP_SEMESTER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new SemesterMapper(), id);
    }

    @Transactional
    public Semester save(Semester semester) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        if (semester.getId() == null) {
            String sql = "INSERT INTO NBP_SEMESTER (NAME, START_DATE, END_DATE) VALUES (?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, semester.getName());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(semester.getStartDate()));
                ps.setTimestamp(3, java.sql.Timestamp.valueOf(semester.getEndDate()));
                return ps;
            }, keyHolder);

            semester.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_SEMESTER SET NAME = ?, START_DATE = ?, END_DATE = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    semester.getName(),
                    semester.getStartDate(),
                    semester.getEndDate(),
                    semester.getId());
        }

        return semester;
    }

    @Transactional
    public void deleteSemesterById(Long id) {
        String sql = "DELETE FROM NBP_SEMESTER WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }
}