package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_LOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ACTION_NAME", nullable = false)
    @NotNull(message = "Action name cannot be null")
    @Size(max = 255, message = "Action name must be at most 255 characters")
    private String actionName;

    @Column(name = "DATE_TIME", nullable = false)
    @NotNull(message = "Date time cannot be null")
    private LocalDateTime dateTime;

    @Column(name = "DB_USER", nullable = false)
    @NotNull(message = "DB user cannot be null")
    @Size(max = 255, message = "DB user must be at most 255 characters")
    private String dbUser;

    @Column(name = "TABLE_NAME", nullable = false)
    @NotNull(message = "Table name cannot be null")
    @Size(max = 255, message = "Table name must be at most 255 characters")
    private String tableName;
}