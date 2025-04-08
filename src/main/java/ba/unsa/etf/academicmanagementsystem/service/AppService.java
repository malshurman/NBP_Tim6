package ba.unsa.etf.academicmanagementsystem.service;

import ba.unsa.etf.academicmanagementsystem.model.App;
import ba.unsa.etf.academicmanagementsystem.repository.AppRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppService {

    private final AppRepository appRepository;

    public List<App> getAllApps() {
        return appRepository.findAllApps();
    }

    public App getAppById(Long id) {
        return appRepository.findAppById(id);
    }

    public App getAppByAppId(String appId) {
        return appRepository.findByAppId(appId);
    }

    public App saveApp(App app) {
        return appRepository.save(app);
    }

    public void deleteApp(Long id) {
        appRepository.deleteAppById(id);
    }
}