// src/main/java/ba/unsa/etf/academicmanagementsystem/controller/AuthController.java
package ba.unsa.etf.academicmanagementsystem.security.controller;

import ba.unsa.etf.academicmanagementsystem.model.User;
import ba.unsa.etf.academicmanagementsystem.security.model.AuthResponse;
import ba.unsa.etf.academicmanagementsystem.security.model.LoginRequest;
import ba.unsa.etf.academicmanagementsystem.security.model.RegisterRequest;
import ba.unsa.etf.academicmanagementsystem.security.service.JwtService;
import ba.unsa.etf.academicmanagementsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        // Assume UserService handles checks for duplicate, etc.
        User user = User.builder()
                .email(request.email())
                .passwordHashed(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .username(request.username())
                .phoneNumber(request.phoneNumber())
                .dateOfBith(request.dateOfBirth().atStartOfDay())
                .roleId(request.roleId())
                .build();
        userService.createUser(user);
        // auto-login after registration:
        String token = jwtService.generateToken(user.getId().toString(), user.getEmail(), "ROLE_USER");
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        User user = userService.getUserByEmail(request.email());
        if (user == null || !passwordEncoder.matches(request.password(), user.getPasswordHashed())) {
            return ResponseEntity.status(401).build();
        }
        String token = jwtService.generateToken(user.getId().toString(), user.getEmail(), "ROLE_USER");
        return ResponseEntity.ok(new AuthResponse(token));
    }
}