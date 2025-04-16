package ba.unsa.etf.academicmanagementsystem.model;

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
public class Role {

    private Long id;

    @NotNull(message = "Name cannot be null")
    @Size(max = 50, message = "Name must be at most 50 characters")
    private String name;
}