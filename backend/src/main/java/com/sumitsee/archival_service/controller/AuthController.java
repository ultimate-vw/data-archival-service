package com.sumitsee.archival_service.controller;

import com.sumitsee.archival_service.dto.AuthRequest;
import com.sumitsee.archival_service.entity.security.AppUser;
import com.sumitsee.archival_service.repository.security.AppUserRepository;
import com.sumitsee.archival_service.security.CustomUserDetailsService;
import com.sumitsee.archival_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userService;
    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository userRepository;


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AppUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(user);
        return ResponseEntity.ok(Map.of("token", token));
    }

}
