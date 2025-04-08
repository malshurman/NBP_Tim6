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
@Table(name = "NBP_SEMESTER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semester {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "START_DATE", nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    @NotNull(message = "End date cannot be null")
    private LocalDateTime endDate;

    @Column(name = "NAME", length = 50, nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
}