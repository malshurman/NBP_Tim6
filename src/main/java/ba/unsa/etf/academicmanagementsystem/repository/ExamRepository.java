package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    @Query(value = "SELECT * FROM NBP_EXAM", nativeQuery = true)
    List<Exam> findAllExams();

    @Query(value = "SELECT * FROM NBP_EXAM WHERE COURSE_ID = ?1", nativeQuery = true)
    List<Exam> findByCourseId(Long courseId);

    @Query(value = "SELECT * FROM NBP_EXAM WHERE ID = ?1", nativeQuery = true)
    Exam findExamById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_EXAM WHERE ID = ?1", nativeQuery = true)
    void deleteExamById(Long id);
}