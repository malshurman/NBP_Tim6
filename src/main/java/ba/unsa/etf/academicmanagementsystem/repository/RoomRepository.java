package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Room;
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
public class RoomRepository {
    private final JdbcTemplate jdbcTemplate;

    public List<Room> findAllRooms() {
        String sql = "SELECT * FROM NBP_ROOM";
        return jdbcTemplate.query(sql, new RoomMapper());
    }

    public Room findRoomById(Long id) {
        String sql = "SELECT * FROM NBP_ROOM WHERE ID = ?";
        return jdbcTemplate.queryForObject(sql, new RoomMapper(), id);
    }

    public Room findByRoomNumber(String roomNumber) {
        String sql = "SELECT * FROM NBP_ROOM WHERE ROOM_NUMBER = ?";
        return jdbcTemplate.queryForObject(sql, new RoomMapper(), roomNumber);
    }

    @Transactional
    public Room save(Room room) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        if (room.getId() == null) {
            String sql = "INSERT INTO NBP_ROOM (CAPACITY, ROOM_NUMBER) VALUES (?, ?)";
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setLong(1, room.getCapacity());
                ps.setString(2, room.getRoomNumber());
                return ps;
            }, keyHolder);
            room.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = "UPDATE NBP_ROOM SET CAPACITY = ?, ROOM_NUMBER = ? WHERE ID = ?";
            jdbcTemplate.update(sql,
                    room.getCapacity(),
                    room.getRoomNumber(),
                    room.getId());
        }
        return room;
    }

    @Transactional
    public void deleteRoomById(Long id) {
        String sql = "DELETE FROM NBP_ROOM WHERE ID = ?";
        jdbcTemplate.update(sql, id);
    }

    private static final class RoomMapper implements RowMapper<Room> {

        @Override
        public Room mapRow(ResultSet rs, int rowNum) throws SQLException {
            Room room = new Room();
            room.setId(rs.getLong("ID"));
            room.setCapacity(rs.getLong("CAPACITY"));
            room.setRoomNumber(rs.getString("ROOM_NUMBER"));
            return room;
        }
    }
}