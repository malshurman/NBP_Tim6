package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {

    @Query(value = "SELECT * FROM APPLICATIONS", nativeQuery = true)
    List<App> findAllApps();

    @Query(value = "SELECT * FROM APPLICATIONS WHERE APP_ID = ?1", nativeQuery = true)
    App findByAppId(String appId);

    @Query(value = "SELECT * FROM APPLICATIONS WHERE ID = ?1", nativeQuery = true)
    App findAppById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM APPLICATIONS WHERE ID = ?1", nativeQuery = true)
    void deleteAppById(Long id);
}