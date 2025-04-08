package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Semester;
import ba.unsa.etf.academicmanagementsystem.repository.SemesterRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SemesterService {

    private final SemesterRepository semesterRepository;

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAllSemesters();
    }

    public Semester getSemesterById(Long id) {
        return semesterRepository.findSemesterById(id);
    }

    public Semester getSemesterByName(String name) {
        return semesterRepository.findByName(name);
    }

    public Semester saveSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    public void deleteSemester(Long id) {
        semesterRepository.deleteSemesterById(id);
    }
}