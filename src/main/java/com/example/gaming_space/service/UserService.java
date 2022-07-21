package com.example.gaming_space.service;

import com.example.gaming_space.dto.PasswordChangeRequest;
import com.example.gaming_space.dto.UserDto;
import com.example.gaming_space.mapper.UserMapper;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final AuthService authService;
    private final UserRepository repo;

    @Autowired
    private final UserMapper mapper;

    @Transactional(readOnly = true)
    public UserDto get() {
        AppUser user = authService.getCurrentUser();
        return mapper.mapUserToDto(user);
    }

    public void changePassword(String password) {
        AppUser user = authService.getCurrentUser();
        user.setPassword(authService.encodePassword(password));
        repo.save(user);
    }

}
