package ba.unsa.etf.academicmanagementsystem.security.service;

import ba.unsa.etf.academicmanagementsystem.model.User;
import ba.unsa.etf.academicmanagementsystem.security.model.AuthResponse;
import ba.unsa.etf.academicmanagementsystem.security.model.LoginRequest;
import ba.unsa.etf.academicmanagementsystem.security.model.RegisterRequest;
import ba.unsa.etf.academicmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthResponse register(RegisterRequest registerRequest) {
        var email = registerRequest.getEmail();
        var passwordHashed = passwordEncoder.encode(registerRequest.getPassword());
        var user = User.builder()
                .email(email)
                .passwordHashed(passwordHashed)
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .username(registerRequest.getUsername())
                .birthDate(registerRequest.getDateOfBirth().toLocalDate().atStartOfDay())
                .roleId(registerRequest.getRoleId())
                .build();
        userService.createUser(user);

        return login(new LoginRequest(email, registerRequest.getPassword()));
    }

    public AuthResponse login(LoginRequest loginRequest) {
        var user = userService.getUserByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHashed())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String accessToken = jwtService.generate(loginRequest.getEmail(), "ACCESS");
        String refreshToken = jwtService.generate(loginRequest.getEmail(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken, user);
    }
}