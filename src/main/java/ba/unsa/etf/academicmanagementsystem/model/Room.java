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
public class Room {

    private Long id;

    @NotNull(message = "Capacity cannot be null")
    @Min(value = 0, message = "Capacity must be at least 0")
    @Max(value = 9999999999L, message = "Capacity must be at most 9999999999")
    private Long capacity;

    @NotNull(message = "Room number cannot be null")
    @Size(max = 10, message = "Room number must be at most 10 characters")
    private String roomNumber;
}