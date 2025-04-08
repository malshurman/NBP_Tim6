package ba.unsa.etf.academicmanagementsystem.repository;

import ba.unsa.etf.academicmanagementsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query(value = "SELECT * FROM NBP_COURSE", nativeQuery = true)
    List<Course> findAllCourses();

    @Query(value = "SELECT * FROM NBP_COURSE WHERE CODE = ?1", nativeQuery = true)
    Course findByCode(String code);

    @Query(value = "SELECT * FROM NBP_COURSE WHERE ID = ?1", nativeQuery = true)
    Course findCourseById(Long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM NBP_COURSE WHERE ID = ?1", nativeQuery = true)
    void deleteCourseById(Long id);
}