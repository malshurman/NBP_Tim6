// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/ExamController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exam Controller", description = "Endpoints for exam creation, scheduling and registration")
public class ExamController {

    @Operation(summary = "Create Exam", description = "Creates and schedules a new exam")
    @PostMapping
    public ResponseEntity<String> createExam(@RequestBody Object examRequest) {
        return ResponseEntity.ok("Exam created");
    }

    @Operation(summary = "List Exams", description = "Retrieves a list of all exams")
    @GetMapping
    public ResponseEntity<String> getAllExams() {
        return ResponseEntity.ok("List of exams");
    }

    @Operation(summary = "Register for Exam", description = "Registers a student for a specific exam")
    @PostMapping("/{examId}/register")
    public ResponseEntity<String> registerForExam(@PathVariable String examId, @RequestBody Object registrationRequest) {
        return ResponseEntity.ok("Student registered for exam: " + examId);
    }

    @Operation(summary = "Get Exam Details", description = "Retrieves details for a specific exam")
    @GetMapping("/{examId}")
    public ResponseEntity<String> getExamDetails(@PathVariable String examId) {
        return ResponseEntity.ok("Details for exam: " + examId);
    }
}