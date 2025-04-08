package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {

    @Query(value = "SELECT * FROM NBP_SEMESTER", nativeQuery = true)
    List<Semester> findAllSemesters();

    @Query(value = "SELECT * FROM NBP_SEMESTER WHERE NAME = ?1", nativeQuery = true)
    Semester findByName(String name);

    @Query(value = "SELECT * FROM NBP_SEMESTER WHERE ID = ?1", nativeQuery = true)
    Semester findSemesterById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_SEMESTER WHERE ID = ?1", nativeQuery = true)
    void deleteSemesterById(Long id);
}