package ba.unsa.etf.academicmanagementsystem.controller;

import ba.unsa.etf.academicmanagementsystem.model.Exam;
import ba.unsa.etf.academicmanagementsystem.service.ExamService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exam Controller", description = "Endpoints for exam creation, scheduling and registration")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @Operation(summary = "Create Exam with PDF", description = "Creates a new exam and stores the exam PDF")
    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Exam> createExam(
            @RequestParam("examDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime examDate,
            @RequestParam("courseId") Long courseId,
            @RequestParam("roomId") Long roomId,
            @RequestParam("examPdf") MultipartFile examPdf) throws IOException {

        Exam exam = Exam.builder()
                .examDate(examDate)
                .courseId(courseId)
                .roomId(roomId)
                .examPdf(examPdf.getBytes())
                .build();
        Exam savedExam = examService.saveExam(exam);
        return ResponseEntity.ok(savedExam);
    }

    @Operation(summary = "List Exams", description = "Retrieves a list of all exams")
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
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

    @Operation(summary = "Retrieve Exam PDF", description = "Downloads the exam PDF for a specific exam")
    @GetMapping("/{examId}/pdf")
    public ResponseEntity<byte[]> downloadExamPdf(@PathVariable Long examId) {
        Exam exam = examService.getExamById(examId);
        if (exam == null || exam.getExamPdf() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDisposition(ContentDisposition.builder("attachment")
                .filename("exam_" + examId + ".pdf")
                .build());
        return ResponseEntity.ok().headers(headers).body(exam.getExamPdf());
    }
}