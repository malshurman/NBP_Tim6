package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.App;
import ba.unsa.etf.academicmanagementsystem.repository.AppRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<App> getAll() {
        return appRepository.findAll();
    }

    public App getById(Long id) {
        return appRepository.findById(id);
    }

    public App getByAppId(String appId) {
        return appRepository.findByAppId(appId);
    }

    public App save(App app) {
        return appRepository.save(app);
    }

    public void delete(Long id) {
        appRepository.deleteById(id);
    }
}