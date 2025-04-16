package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.ExamRegistration;
import ba.unsa.etf.academicmanagementsystem.repository.ExamRegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamRegistrationService {

    private final ExamRegistrationRepository examRegistrationRepository;

    public List<ExamRegistration> getAllExamRegistrations() {
        return examRegistrationRepository.findAllExamRegistrations();
    }

    public ExamRegistration getExamRegistrationById(Long id) {
        return examRegistrationRepository.findById(id);
    }

    public List<ExamRegistration> getExamRegistrationsByStudentId(Long studentId) {
        return examRegistrationRepository.findByStudentId(studentId);
    }

    public ExamRegistration saveExamRegistration(ExamRegistration examRegistration) {
        return examRegistrationRepository.save(examRegistration);
    }

    public void deleteExamRegistration(Long id) {
        examRegistrationRepository.deleteExamRegistrationById(id);
    }
}