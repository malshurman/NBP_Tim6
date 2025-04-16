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
public class App {

    private Long id;

    @NotNull(message = "App ID cannot be null")
    @Size(max = 10, message = "App ID must be at most 10 characters")
    private String appId;

    @NotNull(message = "Expiry Date cannot be null")
    private LocalDateTime expiryDate;

    @NotNull(message = "Manager ID cannot be null")
    private Long managerId;
}