package ba.unsa.etf.academicmanagementsystem.controller;

import ba.unsa.etf.academicmanagementsystem.metrics.service.ReportMetricsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/reports")
@Tag(name = "Report Controller", description = "Endpoints for generating academic and analytics reports")
public class ReportController {
    private final ReportMetricsService reportMetricsService;

    @Operation(summary = "Academic Performance Report", description = "Generates a report on student and course performance")
    @GetMapping("/student-course-metrics")
    public ResponseEntity<byte[]> downloadStudentCourseMetrics() {
        byte[] pdf = reportMetricsService.generateStudentCourseMetricsPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("student_course_metrics.pdf")
                .build());

        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}