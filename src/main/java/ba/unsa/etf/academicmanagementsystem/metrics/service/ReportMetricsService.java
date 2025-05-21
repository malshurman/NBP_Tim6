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

    public byte[] generateStudentCourseMetricsPdf() {
        List<StudentCourseMetricDto> metrics = reportMetricsRepository.getStudentCourseMetrics();
        return ReportGenerator.exportMetricsToPdf(metrics); // Use the export method from above
    }
}
