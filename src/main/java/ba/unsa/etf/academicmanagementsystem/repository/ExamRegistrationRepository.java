package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.ExamRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface ExamRegistrationRepository extends JpaRepository<ExamRegistration, Long> {

    @Query(value = "SELECT * FROM NBP_EXAM_REGISTRATION", nativeQuery = true)
    List<ExamRegistration> findAllExamRegistrations();

    @Query(value = "SELECT * FROM NBP_EXAM_REGISTRATION WHERE STUDENT_ID = ?1", nativeQuery = true)
    List<ExamRegistration> findByStudentId(Long studentId);

    @Query(value = "SELECT * FROM NBP_EXAM_REGISTRATION WHERE ID = ?1", nativeQuery = true)
    ExamRegistration findExamRegistrationById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_EXAM_REGISTRATION WHERE ID = ?1", nativeQuery = true)
    void deleteExamRegistrationById(Long id);
}