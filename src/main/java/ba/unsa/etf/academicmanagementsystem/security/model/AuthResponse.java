package ba.unsa.etf.academicmanagementsystem.security.model;

import ba.unsa.etf.academicmanagementsystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private User user;
}