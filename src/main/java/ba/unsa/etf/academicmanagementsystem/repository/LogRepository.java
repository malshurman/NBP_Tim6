package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Log;
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
public class LogRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Log> findAllLogs() {
        String sql = "SELECT * FROM NBP.NBP_LOG";
        return jdbcTemplate.query(sql, new LogMapper());
    }

    public Log findLogById(Long id) {
        String sql = "SELECT * FROM NBP.NBP_LOG WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new LogMapper(), id);
    }

    public List<Log> findByActionName(String actionName) {
        String sql = "SELECT * FROM NBP.NBP_LOG WHERE ACTION_NAME = ?";
        return jdbcTemplate.query(sql, new LogMapper(), actionName);
    }

    @Transactional
    public Log save(Log log) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (log.getId() == null) {
            String sql = "INSERT INTO NBP.NBP_LOG (ACTION_NAME, DATE_TIME, DB_USER, TABLE_NAME) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, log.getActionName());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(log.getDateTime()));
                ps.setString(3, log.getDbUser());
                ps.setString(4, log.getTableName());
                return ps;
            }, keyHolder);
            log.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP.NBP_LOG SET ACTION_NAME = ?, DATE_TIME = ?, DB_USER = ?, TABLE_NAME = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    log.getActionName(),
                    log.getDateTime(),
                    log.getDbUser(),
                    log.getTableName(),
                    log.getId());
        }
        return log;
    }

    @Transactional
    public void deleteLogById(Long id) {
        String sql = "DELETE FROM NBP.NBP_LOG WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class LogMapper implements RowMapper<Log> {

        @Override
        public Log mapRow(ResultSet rs, int rowNum) throws SQLException {
            Log log = new Log();
            log.setId(rs.getLong("ID"));
            log.setActionName(rs.getString("ACTION_NAME"));
            log.setDateTime(rs.getTimestamp("DATE_TIME").toLocalDateTime());
            log.setDbUser(rs.getString("DB_USER"));
            log.setTableName(rs.getString("TABLE_NAME"));
            return log;
        }
    }
}