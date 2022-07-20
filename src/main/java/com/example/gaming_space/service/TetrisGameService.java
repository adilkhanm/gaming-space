package com.example.gaming_space.service;

import com.example.gaming_space.dto.TetrisRecordDto;
import com.example.gaming_space.mapper.TetrisRecordMapper;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.TetrisRecord;
import com.example.gaming_space.repository.TetrisRecordRepository;
import com.example.gaming_space.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class TetrisGameService {

    private final TetrisRecordRepository repo;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    private final TetrisRecordMapper mapper;

    @Transactional(readOnly = true)
    public List<TetrisRecordDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TetrisRecordDto getBest(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return mapper.mapRecordToDto(repo.findByUserOrderByScoreDesc(user).get(0));
    }

    @Transactional(readOnly = true)
    public List<TetrisRecordDto> getAllOfUser() {
        return repo.findByUser(authService.getCurrentUser())
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(TetrisRecordDto tetrisRecordDto) {
        TetrisRecord tetrisRecord = mapper.mapDtoToRecord(tetrisRecordDto, authService.getCurrentUser());
        repo.save(tetrisRecord);
    }

    @Transactional
    public List<TetrisRecordDto> getByUsername(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return repo.findByUser(user)
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

}
