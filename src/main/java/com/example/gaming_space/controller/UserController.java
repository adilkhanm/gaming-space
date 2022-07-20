package com.example.gaming_space.controller;

import com.example.gaming_space.dto.PasswordChangeRequest;
import com.example.gaming_space.dto.UserDto;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/profile")
@AllArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<UserDto> get() {
        return status(HttpStatus.OK).body(service.get());
    }

    @PostMapping("/change_password")
    public ResponseEntity<Void> updatePassword(PasswordChangeRequest request) {
        return new ResponseEntity<>((request.getPassword().equals(request.getRepassword())) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
}
