package com.example.gaming_space.service;

import com.example.gaming_space.dto.AuthenticationResponse;
import com.example.gaming_space.dto.LoginRequest;
import com.example.gaming_space.dto.RefreshTokenRequest;
import com.example.gaming_space.dto.RegisterRequest;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.repository.UserRepository;
import com.example.gaming_space.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static java.time.Instant.now;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public ResponseEntity<String> signup(RegisterRequest registerRequest) {
        log.info("register: " + registerRequest);
        AppUser user = new AppUser();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(this.encodePassword(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(true);
        if (this.userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            return new ResponseEntity("The username is already in use", HttpStatus.UNAUTHORIZED);
        } else if (this.userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return new ResponseEntity("User with such email already exists", HttpStatus.UNAUTHORIZED);
        } else {
            log.info("User => " + user.toString());
            this.userRepository.save(user);
            log.info("User Registered Successfully!");
            return new ResponseEntity("User Registration Successful", HttpStatus.OK);
        }
    }

    @Transactional(readOnly = true)
    AppUser getCurrentUser() {
        org.springframework.security.core.userdetails.User principal =
                (org.springframework.security.core.userdetails.User)
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUsername(principal.getUsername())
                .orElseThrow(() -> new
                        UsernameNotFoundException("User name not found: " + principal.getUsername()));
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode((password));
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String authenticationToken = jwtProvider.generateToken(authenticate);
        return AuthenticationResponse.builder()
                .authenticationToken(authenticationToken)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .build();

    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenWithUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
