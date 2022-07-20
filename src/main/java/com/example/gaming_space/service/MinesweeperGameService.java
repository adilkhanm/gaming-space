package com.example.gaming_space.service;

import com.example.gaming_space.dto.MinesweeperRecordDto;
import com.example.gaming_space.dto.TetrisRecordDto;
import com.example.gaming_space.mapper.MinesweeperRecordMapper;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.MinesweeperRecord;
import com.example.gaming_space.repository.MinesweeperRecordRepository;
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
public class MinesweeperGameService {

    private final MinesweeperRecordRepository repo;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    private final MinesweeperRecordMapper mapper;

    @Transactional(readOnly = true)
    public List<MinesweeperRecordDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MinesweeperRecordDto getBest(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return mapper.mapRecordToDto(repo.findByUserOrderByScoreDesc(user).get(0));
    }

    @Transactional(readOnly = true)
    public List<MinesweeperRecordDto> getAllOfUser() {
        return repo.findByUser(authService.getCurrentUser())
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(MinesweeperRecordDto minesweeperRecordDto) {
        MinesweeperRecord minesweeperRecord = mapper.mapDtoToRecord(minesweeperRecordDto, authService.getCurrentUser());
        repo.save(minesweeperRecord);
    }

    @Transactional(readOnly = true)
    public List<MinesweeperRecordDto> getByUsername(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return repo.findByUser(user)
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

}
