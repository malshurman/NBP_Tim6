package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Exam;
import ba.unsa.etf.academicmanagementsystem.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamRepository examRepository;

    public List<Exam> getAllExams() {
        return examRepository.findAllExams();
    }

    public Exam getExamById(Long id) {
        return examRepository.findById(id);
    }

    public List<Exam> getExamsByCourseId(Long courseId) {
        return examRepository.findByCourseId(courseId);
    }

    public Exam saveExam(Exam exam) {
        return examRepository.save(exam);
    }

    public void deleteExam(Long id) {
        examRepository.deleteExamById(id);
    }
}