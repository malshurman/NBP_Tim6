// File: src/main/java/ba/unsa/etf/academicmanagementsystem/controller/UserController.java
package ba.unsa.etf.academicmanagementsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User Controller", description = "Endpoints for user authentication, registration and profile management")
public class UserController {

    @Operation(summary = "User Login", description = "Performs user authentication and returns a JWT token")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Object loginRequest) {
        return ResponseEntity.ok("User logged in");
    }

    @Operation(summary = "User Registration", description = "Registers a new user")
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Object registrationRequest) {
        return ResponseEntity.ok("User registered");
    }

    @Operation(summary = "Get User Profile", description = "Retrieves profile information for a specific user")
    @GetMapping("/{id}/profile")
    public ResponseEntity<String> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok("User profile for id: " + id);
    }

    @Operation(summary = "Update User Profile", description = "Updates profile information for a specific user")
    @PutMapping("/{id}/profile")
    public ResponseEntity<String> updateProfile(@PathVariable Long id, @RequestBody Object updateRequest) {
        return ResponseEntity.ok("User profile updated for id: " + id);
    }
}