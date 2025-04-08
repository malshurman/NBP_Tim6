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
@Table(name = "NBP_APPS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class App {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "APP_ID", length = 10, nullable = false)
    @NotNull(message = "App ID cannot be null")
    @Size(max = 10, message = "App ID must be at most 10 characters")
    private String appId;

    @Column(name = "EXPIRY_DATE", nullable = false)
    @NotNull(message = "Expiry Date cannot be null")
    private LocalDateTime expiryDate;

    @Column(name = "MANAGER_ID", nullable = false)
    @NotNull(message = "Manager ID cannot be null")
    private Long managerId;
}