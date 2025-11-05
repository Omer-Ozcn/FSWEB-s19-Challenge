package com.workintech.twitter_api.controller;

import com.workintech.twitter_api.entity.User;
import com.workintech.twitter_api.exceptions.NotFoundException;
import com.workintech.twitter_api.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterReq req) {
        if (userRepo.existsByUsername(req.username()) || userRepo.existsByEmail(req.email())) {
            return ResponseEntity.status(409).build();
        }
        User u = new User();
        u.setUsername(req.username());
        u.setEmail(req.email());
        u.setPasswordHash(encoder.encode(req.password()));
        userRepo.save(u);
        return ResponseEntity.status(201).build();
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginReq req) {
        User u = userRepo.findByUsername(req.username())
                .orElseThrow(() -> new NotFoundException("User not found"));
        boolean ok = encoder.matches(req.password(), u.getPasswordHash());
        return ok ? ResponseEntity.ok().build() : ResponseEntity.status(401).build();
    }

    public record RegisterReq(
            @NotBlank @Size(max = 50) String username,
            @NotBlank @Email @Size(max = 120) String email,
            @NotBlank @Size(min = 6, max = 255) String password
    ) {}

    public record LoginReq(
            @NotBlank String username,
            @NotBlank String password
    ) {}
}
