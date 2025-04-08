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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "NBP_COURSE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CODE", length = 20, nullable = false, unique = true)
    @NotNull(message = "Code cannot be null")
    @Size(max = 20, message = "Code must be at most 20 characters")
    private String code;

    @Column(name = "CREDITS", nullable = false)
    @NotNull(message = "Credits cannot be null")
    @Min(value = 0, message = "Credits must be at least 0")
    @Max(value = 9999999999L, message = "Credits must be at most 9999999999")
    private Long credits;

    @Column(name = "NAME", nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @Column(name = "PROFESSOR_ID", nullable = false)
    @NotNull(message = "Professor ID cannot be null")
    private Long professorId;

    @Column(name = "SEMESTER_ID", nullable = false)
    @NotNull(message = "Semester ID cannot be null")
    private Long semesterId;
}