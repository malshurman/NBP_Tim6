// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/NotificationController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/notifications")
@Tag(name = "Notification Controller", description = "Endpoints for managing exam, grade and announcement notifications")
public class NotificationController {

    @Operation(summary = "Exam Notifications", description = "Retrieves exam-related notifications")
    @GetMapping("/exam")
    public ResponseEntity<String> getExamNotifications() {
        return ResponseEntity.ok("Exam notifications");
    }

    @Operation(summary = "Grade Notifications", description = "Retrieves grade-related notifications")
    @GetMapping("/grade")
    public ResponseEntity<String> getGradeNotifications() {
        return ResponseEntity.ok("Grade notifications");
    }

    @Operation(summary = "Create Announcement", description = "Publishes a system-wide announcement")
    @PostMapping("/announcement")
    public ResponseEntity<String> createAnnouncement(@RequestBody Object announcement) {
        return ResponseEntity.ok("Announcement created");
    }
}