package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private Long id;

    @NotNull(message = "Code cannot be null")
    @Size(max = 20, message = "Code must be at most 20 characters")
    private String code;

    @NotNull(message = "Credits cannot be null")
    @Min(value = 0, message = "Credits must be at least 0")
    @Max(value = 9999999999L, message = "Credits must be at most 9999999999")
    private Long credits;

    @NotNull(message = "Name cannot be null")
    @Size(max = 255, message = "Name must be at most 255 characters")
    private String name;

    @NotNull(message = "Professor ID cannot be null")
    private Long professorId;

    @NotNull(message = "Semester ID cannot be null")
    private Long semesterId;
}