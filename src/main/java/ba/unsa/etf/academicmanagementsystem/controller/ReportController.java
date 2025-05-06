// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/ReportController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report Controller", description = "Endpoints for generating academic and analytics reports")
public class ReportController {

    @Operation(summary = "Academic Performance Report", description = "Generates a report on student and course performance")
    @GetMapping("/academic-performance")
    public ResponseEntity<String> getAcademicPerformanceReport() {
        return ResponseEntity.ok("Academic performance report");
    }

    @Operation(summary = "Attendance Report", description = "Generates a report on class attendance")
    @GetMapping("/attendance")
    public ResponseEntity<String> getAttendanceReport() {
        return ResponseEntity.ok("Attendance report");
    }

    @Operation(summary = "Graduation Report", description = "Generates a report on graduation and degree completion progress")
    @GetMapping("/graduation")
    public ResponseEntity<String> getGraduationReport() {
        return ResponseEntity.ok("Graduation report");
    }

    @Operation(summary = "Faculty Performance Report", description = "Generates a report on faculty performance based on feedback and metrics")
    @GetMapping("/faculty-performance")
    public ResponseEntity<String> getFacultyPerformanceReport() {
        return ResponseEntity.ok("Faculty performance report");
    }
}