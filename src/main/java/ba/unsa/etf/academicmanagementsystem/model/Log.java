package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {

    private Long id;

    @NotNull(message = "Action name cannot be null")
    @Size(max = 255, message = "Action name must be at most 255 characters")
    private String actionName;

    @NotNull(message = "Date time cannot be null")
    private LocalDateTime dateTime;

    @NotNull(message = "DB user cannot be null")
    @Size(max = 255, message = "DB user must be at most 255 characters")
    private String dbUser;

    @NotNull(message = "Table name cannot be null")
    @Size(max = 255, message = "Table name must be at most 255 characters")
    private String tableName;
}