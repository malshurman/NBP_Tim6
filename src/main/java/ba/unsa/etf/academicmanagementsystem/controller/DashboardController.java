// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/DashboardController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@Tag(name = "Dashboard Controller", description = "Endpoints for personalized dashboard views based on user roles")
public class DashboardController {

    @Operation(summary = "Student Dashboard", description = "Provides dashboard data for a student")
    @GetMapping("/student/{studentId}")
    public ResponseEntity<String> getStudentDashboard(@PathVariable Long studentId) {
        return ResponseEntity.ok("Dashboard for student: " + studentId);
    }

    @Operation(summary = "Professor Dashboard", description = "Provides dashboard data for a professor")
    @GetMapping("/professor/{professorId}")
    public ResponseEntity<String> getProfessorDashboard(@PathVariable Long professorId) {
        return ResponseEntity.ok("Dashboard for professor: " + professorId);
    }

    @Operation(summary = "Admin Dashboard", description = "Provides system-wide dashboard information for administrators")
    @GetMapping("/admin")
    public ResponseEntity<String> getAdminDashboard() {
        return ResponseEntity.ok("Admin dashboard");
    }
}