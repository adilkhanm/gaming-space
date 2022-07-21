package com.example.gaming_space.controller;

import com.example.gaming_space.dto.PasswordChangeRequest;
import com.example.gaming_space.dto.UserDto;
import com.example.gaming_space.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Void> updatePassword(@RequestBody PasswordChangeRequest request) {
        if (request.getPassword().equals(request.getRepassword())) {
            service.changePassword(request.getPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
