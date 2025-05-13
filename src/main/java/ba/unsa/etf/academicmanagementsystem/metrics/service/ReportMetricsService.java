package ba.unsa.etf.academicmanagementsystem.metrics.service;

import ba.unsa.etf.academicmanagementsystem.metrics.dto.StudentCourseMetricDto;
import ba.unsa.etf.academicmanagementsystem.metrics.pdf.ReportGenerator;
import ba.unsa.etf.academicmanagementsystem.metrics.repository.ReportMetricsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReportMetricsService {
    private final ReportMetricsRepository reportMetricsRepository;

    public byte[] generateStudentCourseMetricsPdf() throws Exception {
        List<StudentCourseMetricDto> metrics = reportMetricsRepository.getStudentCourseMetrics();
        metrics.forEach(metric -> {
            System.out.println("Student ID: " + metric.getStudentId());
            System.out.println("First Name: " + metric.getFirstName());
            System.out.println("Last Name: " + metric.getLastName());
            System.out.println("Course ID: " + metric.getCourseId());
            System.out.println("Course Name: " + metric.getCourseName());
            System.out.println("Credits: " + metric.getCredits());
            System.out.println("Number of Grades: " + metric.getNumGrades());
            System.out.println("Total Grade: " + metric.getTotalGrade());
            System.out.println("Average Grade: " + metric.getAvgGrade());
            System.out.println("Minimum Grade: " + metric.getMinGrade());
            System.out.println("Maximum Grade: " + metric.getMaxGrade());
            System.out.println("------------------------------------");
        });
        return ReportGenerator.exportMetricsToPdf(metrics); // Use the export method from above
    }
}
