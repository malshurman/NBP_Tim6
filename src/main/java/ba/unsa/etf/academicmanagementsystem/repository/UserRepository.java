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
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (user.getId() == null) {
            String sql = "INSERT INTO NBP.NBP_USER (ADDRESS_ID, BIRTH_DATE, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, PHONE_NUMBER, USERNAME, ROLE_ID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, user.getAddressId());
                ps.setTimestamp(2, user.getBirthDate() != null ? java.sql.Timestamp.valueOf(user.getBirthDate()) : null);
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getFirstName());
                ps.setString(5, user.getLastName());
                ps.setString(6, user.getPassword());
                ps.setString(7, user.getPhoneNumber());
                ps.setString(8, user.getUsername());
                ps.setLong(9, user.getRoleId());
                return ps;
            }, keyHolder);
            user.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP.NBP_USER SET ADDRESS_ID = ?, BIRTH_DATE = ?, EMAIL = ?, FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ?, PHONE_NUMBER = ?, USERNAME = ?, ROLE_ID = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    user.getAddressId(),
                    user.getBirthDate() != null ? java.sql.Timestamp.valueOf(user.getBirthDate()) : null,
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPassword(),
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
            user.setAddressId(rs.getLong("ADDRESS_ID"));
            user.setBirthDate(rs.getTimestamp("BIRTH_DATE").toLocalDateTime());
            user.setEmail(rs.getString("EMAIL"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setLastName(rs.getString("LAST_NAME"));
            user.setPassword(rs.getString("PASSWORD"));
            user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            user.setUsername(rs.getString("USERNAME"));
            user.setRoleId(rs.getLong("ROLE_ID"));
            return user;
        }
    }
}