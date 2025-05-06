package ba.unsa.etf.academicmanagementsystem.security.controller;

import ba.unsa.etf.academicmanagementsystem.security.model.AuthResponse;
import ba.unsa.etf.academicmanagementsystem.security.model.LoginRequest;
import ba.unsa.etf.academicmanagementsystem.security.model.RegisterRequest;
import ba.unsa.etf.academicmanagementsystem.security.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        System.out.println("register request: " + registerRequest);
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}