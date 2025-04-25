// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/CourseController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@Tag(name = "Course Controller", description = "Endpoints for course management and student enrollment")
public class CourseController {

    @Operation(summary = "List All Courses", description = "Retrieves a list of all available courses")
    @GetMapping
    public ResponseEntity<String> getAllCourses() {
        return ResponseEntity.ok("List of courses");
    }

    @Operation(summary = "Create Course", description = "Creates a new course")
    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody Object courseRequest) {
        return ResponseEntity.ok("Course created");
    }

    @Operation(summary = "Enroll Student", description = "Enrolls a student in a course")
    @PostMapping("/{courseId}/enroll")
    public ResponseEntity<String> enrollStudent(@PathVariable String courseId, @RequestBody Object enrollmentRequest) {
        return ResponseEntity.ok("Student enrolled in course: " + courseId);
    }

    @Operation(summary = "Get Course Details", description = "Retrieves details for a specific course")
    @GetMapping("/{courseId}")
    public ResponseEntity<String> getCourseDetails(@PathVariable String courseId) {
        return ResponseEntity.ok("Course details for: " + courseId);
    }
}