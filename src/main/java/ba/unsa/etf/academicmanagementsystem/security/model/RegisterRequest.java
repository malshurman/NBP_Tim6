package ba.unsa.etf.academicmanagementsystem.security.model;

import java.time.LocalDate;

public record RegisterRequest(
        String email,
        String password,
        String firstName,
        String lastName,
        String username,
        String phoneNumber,
        LocalDate dateOfBirth,
        Long roleId) {
}