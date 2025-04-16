package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.App;
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
public class AppRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<App> findAll() {
        String sql = "SELECT * FROM NBP.NBP_APPS";
        return jdbcTemplate.query(sql, new AppMapper());
    }

    public App findByAppId(String appId) {
        String sql = "SELECT * FROM NBP.NBP_APPS WHERE APP_ID = ?";
        return jdbcTemplate.queryForObject(sql, new AppMapper(), appId);
    }

    public App findById(Long id) {
        String sql = "SELECT * FROM NBP.NBP_APPS WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new AppMapper(), id);
    }

    @Transactional
    public App save(App app) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (app.getId() == null) {
            String sql = "INSERT INTO NBP.NBP_APPS (APP_ID, EXPIRY_DATE, MANAGER_ID) VALUES (?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, app.getAppId());
                ps.setTimestamp(2, java.sql.Timestamp.valueOf(app.getExpiryDate()));
                ps.setLong(3, app.getManagerId());
                return ps;
            }, keyHolder);
            app.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP.NBP_APPS SET APP_ID = ?, EXPIRY_DATE = ?, MANAGER_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    app.getAppId(),
                    app.getExpiryDate(),
                    app.getManagerId(),
                    app.getId());
        }
        return app;
    }

    @Transactional
    public void deleteById(Long id) {
        String sql = "DELETE FROM NBP.NBP_APPS WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class AppMapper implements RowMapper<App> {
        @Override
        public App mapRow(ResultSet rs, int rowNum) throws SQLException {
            App app = new App();
            app.setId(rs.getLong("ID"));
            app.setAppId(rs.getString("APP_ID"));
            app.setExpiryDate(rs.getTimestamp("EXPIRY_DATE").toLocalDateTime());
            app.setManagerId(rs.getLong("MANAGER_ID"));
            return app;
        }
    }
}