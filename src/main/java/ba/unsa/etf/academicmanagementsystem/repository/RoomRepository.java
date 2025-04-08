package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "SELECT * FROM NBP_ROOM", nativeQuery = true)
    List<Room> findAllRooms();

    @Query(value = "SELECT * FROM NBP_ROOM WHERE ROOM_NUMBER = ?1", nativeQuery = true)
    Room findByRoomNumber(String roomNumber);

    @Query(value = "SELECT * FROM NBP_ROOM WHERE ID = ?1", nativeQuery = true)
    Room findRoomById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_ROOM WHERE ID = ?1", nativeQuery = true)
    void deleteRoomById(Long id);
}