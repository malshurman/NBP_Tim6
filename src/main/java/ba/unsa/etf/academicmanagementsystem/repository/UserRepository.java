package ba.unsa.etf.academicmanagementsystem.repository;

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
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<User> findAllUsers() {
        String sql = "SELECT * FROM NBP.NBP_USER";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public User findUserById(Long id) {
        String sql = "SELECT * FROM NBP.NBP_USER WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), id);
    }

    public User findByUsername(String username) {
        String sql = "SELECT * FROM NBP.NBP_USER WHERE USERNAME = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), username);
    }

    public User findByEmail(String email) {
        String sql = "SELECT * FROM NBP.NBP_USER WHERE EMAIL = ?";
        return jdbcTemplate.queryForObject(sql, new UserMapper(), email);
    }

    @Transactional
    public User save(User user) {
        if (user.getId() == null) {
            String sql = "INSERT INTO NBP.NBP_USER (BIRTH_DATE, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, " +
                    "PHONE_NUMBER, USERNAME, ROLE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            // Specify column names for generated keys - this is important for Oracle
            String[] returnColumns = {"ID"};
            KeyHolder keyHolder = new GeneratedKeyHolder();

            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, returnColumns);
                ps.setTimestamp(1, user.getDateOfBith() != null ?
                        java.sql.Timestamp.valueOf(user.getDateOfBith()) : null);
                ps.setString(2, user.getEmail());
                ps.setString(3, user.getFirstName());
                ps.setString(4, user.getLastName());
                ps.setString(5, user.getPasswordHashed());
                ps.setString(6, user.getPhoneNumber());
                ps.setString(7, user.getUsername());
                ps.setLong(8, user.getRoleId());
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

            // If still null, query the database using username (as fallback)
            if (generatedId == null) {
                try {
                    User retrievedUser = findByUsername(user.getUsername());
                    if (retrievedUser != null) {
                        generatedId = retrievedUser.getId();
                    }
                } catch (Exception ignored) {
                    // The query might fail if user doesn't exist yet
                }
            }

            if (generatedId != null) {
                user.setId(generatedId);
            } else {
                throw new RuntimeException("Failed to retrieve generated ID for user");
            }
        } else {
            // Update code remains unchanged
            String sql = "UPDATE NBP.NBP_USER SET BIRTH_DATE = ?, EMAIL = ?, FIRST_NAME = ?, " +
                    "LAST_NAME = ?, PASSWORD = ?, PHONE_NUMBER = ?, USERNAME = ?, ROLE_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    user.getDateOfBith() != null ? java.sql.Timestamp.valueOf(user.getDateOfBith()) : null,
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPasswordHashed(),
                    user.getPhoneNumber(),
                    user.getUsername(),
                    user.getRoleId(),
                    user.getId());
        }
        return user;
    }

    @Transactional
    public void deleteUserById(Long id) {
        String sql = "DELETE FROM NBP.NBP_USER WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("ID"));
            user.setDateOfBith(rs.getTimestamp("BIRTH_DATE").toLocalDateTime());
            user.setEmail(rs.getString("EMAIL"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setPasswordHashed(rs.getString("PASSWORD"));
            user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            user.setUsername(rs.getString("USERNAME"));
            user.setRoleId(rs.getLong("ROLE_ID"));
            return user;
        }
    }
}