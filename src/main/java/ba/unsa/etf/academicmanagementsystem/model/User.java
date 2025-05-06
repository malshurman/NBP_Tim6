package ba.unsa.etf.academicmanagementsystem.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
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
public class User {

    private Long id;

    @Past(message = "Birth date must be in the past")
    private LocalDateTime birthDate;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "First name must be at most 255 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name must be at most 255 characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(max = 255, message = "Password must be at most 255 characters")
    private String passwordHashed;

    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phoneNumber;

    @NotNull(message = "Username cannot be null")
    @Size(max = 255, message = "Username must be at most 255 characters")
    private String username;

    private Long roleId;
}