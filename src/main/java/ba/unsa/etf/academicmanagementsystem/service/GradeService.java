package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Grade;
import ba.unsa.etf.academicmanagementsystem.repository.GradeRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;

    public List<Grade> getAllGrades() {
        return gradeRepository.findAllGrades();
    }

    public Grade getGradeById(Long id) {
        return gradeRepository.findGradeById(id);
    }

    public List<Grade> getGradesByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteGradeById(id);
    }
}