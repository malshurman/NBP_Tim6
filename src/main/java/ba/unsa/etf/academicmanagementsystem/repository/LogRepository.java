package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

    @Query(value = "SELECT * FROM NBP_LOG", nativeQuery = true)
    List<Log> findAllLogs();

    @Query(value = "SELECT * FROM NBP_LOG WHERE ACTION_NAME = ?1", nativeQuery = true)
    List<Log> findByActionName(String actionName);

    @Query(value = "SELECT * FROM NBP_LOG WHERE ID = ?1", nativeQuery = true)
    Log findLogById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_LOG WHERE ID = ?1", nativeQuery = true)
    void deleteLogById(Long id);
}