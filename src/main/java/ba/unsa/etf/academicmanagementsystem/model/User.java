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
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_USER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ADDRESS_ID")
    private Long addressId;

    @Column(name = "BIRTH_DATE")
    @Past(message = "Birth date must be in the past")
    private LocalDateTime birthDate;

    @Column(name = "EMAIL", nullable = false)
    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    @Size(max = 255, message = "Email must be at most 255 characters")
    private String email;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull(message = "First name cannot be null")
    @Size(max = 255, message = "First name must be at most 255 characters")
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull(message = "Last name cannot be null")
    @Size(max = 255, message = "Last name must be at most 255 characters")
    private String lastName;

    @Column(name = "PASSWORD", nullable = false)
    @NotNull(message = "Password cannot be null")
    @Size(max = 255, message = "Password must be at most 255 characters")
    private String password;

    @Column(name = "PHONE_NUMBER", length = 20)
    @Size(max = 20, message = "Phone number must be at most 20 characters")
    private String phoneNumber;

    @Column(name = "USERNAME", nullable = false)
    @NotNull(message = "Username cannot be null")
    @Size(max = 255, message = "Username must be at most 255 characters")
    private String username;

    @Column(name = "ROLE_ID")
    private Long roleId;
}