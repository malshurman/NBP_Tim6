package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Role;
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
public class RoleRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Role> findAllRoles() {
        String sql = "SELECT * FROM NBP.NBP_ROLE";
        return jdbcTemplate.query(sql, new RoleMapper());
    }

    public Role findByName(String name) {
        String sql = "SELECT * FROM NBP.NBP_ROLE WHERE NAME = ?";
        return jdbcTemplate.queryForObject(sql, new RoleMapper(), name);
    }

    public Role findRoleById(Long id) {
        String sql = "SELECT * FROM NBP.NBP_ROLE WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new RoleMapper(), id);
    }

    @Transactional
    public Role save(Role role) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (role.getId() == null) {
            String sql = "INSERT INTO NBP.NBP_ROLE (NAME) VALUES (?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, role.getName());
                return ps;
            }, keyHolder);
            role.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP.NBP_ROLE SET NAME = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    role.getName(),
                    role.getId());
        }
        return role;
    }

    @Transactional
    public void deleteRoleById(Long id) {
        String sql = "DELETE FROM NBP.NBP_ROLE WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class RoleMapper implements RowMapper<Role> {
        @Override
        public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
            Role role = new Role();
            role.setId(rs.getLong("ID"));
            role.setName(rs.getString("NAME"));
            return role;
        }
    }
}