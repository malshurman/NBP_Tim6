package ba.unsa.etf.academicmanagementsystem.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.sql.Timestamp;

@Entity
@Table(name = "NBP_LOG")
@Data
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String actionName;

    @NotBlank
    @Size(max = 255)
    private String tableName;

    @NotNull
    private Timestamp dateTime;

    @Size(max = 255)
    private String dbUser;
}