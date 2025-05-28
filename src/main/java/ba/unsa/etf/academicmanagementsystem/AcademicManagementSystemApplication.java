package ba.unsa.etf.academicmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AcademicManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AcademicManagementSystemApplication.class, args);
    }

}
