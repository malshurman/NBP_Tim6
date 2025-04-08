package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {

    @Query(value = "SELECT * FROM NBP_GRADE", nativeQuery = true)
    List<Grade> findAllGrades();

    @Query(value = "SELECT * FROM NBP_GRADE WHERE STUDENT_ID = ?1", nativeQuery = true)
    List<Grade> findByStudentId(Long studentId);

    @Query(value = "SELECT * FROM NBP_GRADE WHERE ID = ?1", nativeQuery = true)
    Grade findGradeById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_GRADE WHERE ID = ?1", nativeQuery = true)
    void deleteGradeById(Long id);
}