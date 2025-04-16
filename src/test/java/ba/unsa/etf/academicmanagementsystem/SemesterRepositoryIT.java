package ba.unsa.etf.academicmanagementsystem;

import ba.unsa.etf.academicmanagementsystem.model.Semester;
import ba.unsa.etf.academicmanagementsystem.repository.SemesterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
@Transactional
public class SemesterRepositoryIT {

    @Autowired
    private SemesterRepository semesterJdbcRepository;

    @Test
    public void testFindAllSemesters() {
        List<Semester> semesters = semesterJdbcRepository.findAllSemesters();
        assertThat(semesters).isNotNull();
        assertThat(semesters.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    public void testSaveAndFindByName() {
        Semester semester = new Semester();
        semester.setName("Spring 2023");
        semester.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        semester.setEndDate(LocalDateTime.of(2023, 6, 30, 23, 59));

        // Save the semester first
        semesterJdbcRepository.save(semester);

        Semester foundSemester = semesterJdbcRepository.findByName("Spring 2023");
        assertThat(foundSemester).isNotNull();
        assertThat(foundSemester.getName()).isEqualTo("Spring 2023");
    }

    @Test
    public void testFindSemesterById() {
        Semester semester = new Semester();
        semester.setName("Fall 2023");
        semester.setStartDate(LocalDateTime.of(2023, 9, 1, 0, 0));
        semester.setEndDate(LocalDateTime.of(2023, 12, 31, 23, 59));

        // Save the semester and get the generated ID
        Semester savedSemester = semesterJdbcRepository.save(semester);
        Long semesterId = savedSemester.getId();

        // Test with the actual saved ID
        Semester foundSemester = semesterJdbcRepository.findSemesterById(semesterId);
        assertThat(foundSemester).isNotNull();
        assertThat(foundSemester.getName()).isEqualTo("Fall 2023");
    }

    @Test
    public void testDeleteSemesterById() {
        Semester semester = new Semester();
        semester.setName("Winter 2023");
        semester.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        semester.setEndDate(LocalDateTime.of(2023, 3, 31, 23, 59));

        // Save the semester and get the generated ID
        Semester savedSemester = semesterJdbcRepository.save(semester);
        Long semesterId = savedSemester.getId();

        // Test with the actual saved ID
        semesterJdbcRepository.deleteSemesterById(semesterId);

        // Verify semester was deleted - need to handle exception for this test
        try {
            Semester deletedSemester = semesterJdbcRepository.findSemesterById(semesterId);
            assertThat(deletedSemester).isNull();
        } catch (org.springframework.dao.EmptyResultDataAccessException e) {
            // This is expected after deletion
            // Test passes if exception is thrown
        }
    }
}