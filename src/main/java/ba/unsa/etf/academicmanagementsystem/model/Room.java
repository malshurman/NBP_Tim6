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
@Table(name = "NBP_ROOM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CAPACITY", nullable = false)
    @NotNull(message = "Capacity cannot be null")
    @Min(value = 0, message = "Capacity must be at least 0")
    @Max(value = 9999999999L, message = "Capacity must be at most 9999999999")
    private Long capacity;

    @Column(name = "ROOM_NUMBER", length = 10, nullable = false, unique = true)
    @NotNull(message = "Room number cannot be null")
    @Size(max = 10, message = "Room number must be at most 10 characters")
    private String roomNumber;
}