package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Course;
import ba.unsa.etf.academicmanagementsystem.repository.CourseRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAllCourses();
    }

    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    public Course getCourseByCode(String code) {
        return courseRepository.findByCode(code);
    }

    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteCourseById(id);
    }
}