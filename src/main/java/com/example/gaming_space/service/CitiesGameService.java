package com.example.gaming_space.service;

import com.example.gaming_space.dto.CitiesGameRecordDto;
import com.example.gaming_space.dto.TetrisRecordDto;
import com.example.gaming_space.mapper.CitiesGameRecordMapper;
import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.CitiesGameRecord;
import com.example.gaming_space.repository.CitiesGameRecordRepository;
import com.example.gaming_space.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class CitiesGameService {

    private final CitiesGameRecordRepository repo;
    private final UserRepository userRepository;
    private final AuthService authService;

    @Autowired
    private final CitiesGameRecordMapper mapper;

    @Transactional(readOnly = true)
    public List<CitiesGameRecordDto> getAll() {
        return repo.findAll()
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CitiesGameRecordDto getBest(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return mapper.mapRecordToDto(repo.findByUserOrderByScoreDesc(user).get(0));
    }

    @Transactional(readOnly = true)
    public List<CitiesGameRecordDto> getAllOfUser() {
        return repo.findByUser(authService.getCurrentUser())
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void save(CitiesGameRecordDto citiesGameRecordDto) {
        CitiesGameRecord citiesGameRecord = mapper.mapDtoToRecord(citiesGameRecordDto, authService.getCurrentUser());
        repo.save(citiesGameRecord);
    }

    @Transactional
    public List<CitiesGameRecordDto> getByUsername(String username) {
        AppUser user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return repo.findByUser(user)
                .stream()
                .map(mapper::mapRecordToDto)
                .collect(Collectors.toList());
    }

}
