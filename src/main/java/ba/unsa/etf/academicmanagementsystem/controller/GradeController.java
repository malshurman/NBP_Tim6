// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/GradeController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/grades")
@Tag(name = "Grade Controller", description = "Endpoints for managing grade submissions and transcript generation")
public class GradeController {

    @Operation(summary = "Submit Grade", description = "Submits a grade after exam evaluation")
    @PostMapping
    public ResponseEntity<String> submitGrade(@RequestBody Object gradeRequest) {
        return ResponseEntity.ok("Grade submitted");
    }

    @Operation(summary = "Generate Transcript", description = "Generates a transcript for a given student")
    @GetMapping("/transcript/{studentId}")
    public ResponseEntity<String> generateTranscript(@PathVariable Long studentId) {
        return ResponseEntity.ok("Transcript for student: " + studentId);
    }

    @Operation(summary = "Get Grade Details", description = "Fetches detailed information for a specific grade")
    @GetMapping("/{gradeId}")
    public ResponseEntity<String> getGradeDetails(@PathVariable Long gradeId) {
        return ResponseEntity.ok("Grade details for: " + gradeId);
    }
}