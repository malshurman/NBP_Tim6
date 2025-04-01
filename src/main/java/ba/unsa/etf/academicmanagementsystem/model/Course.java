package ba.unsa.etf.academicmanagementsystem.model;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "NBP_COURSE")
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String code;

    @NotNull
    private Integer credits;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private Semester semester;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private User professor;
}