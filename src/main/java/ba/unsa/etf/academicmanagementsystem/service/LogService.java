package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.Log;
import ba.unsa.etf.academicmanagementsystem.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<Log> getAllLogs() {
        return logRepository.findAllLogs();
    }

    public Log getLogById(Long id) {
        return logRepository.findLogById(id);
    }

    public List<Log> getLogsByActionName(String actionName) {
        return logRepository.findByActionName(actionName);
    }

    public Log saveLog(Log log) {
        return logRepository.save(log);
    }

    public void deleteLog(Long id) {
        logRepository.deleteLogById(id);
    }
}