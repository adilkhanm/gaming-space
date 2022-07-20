package com.example.gaming_space.controller;

import com.example.gaming_space.dto.CitiesGameRecordDto;
import com.example.gaming_space.service.CitiesGameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/cities")
@AllArgsConstructor
@Slf4j
public class CitiesGameController {

    private final CitiesGameService service;

    @GetMapping
    public ResponseEntity<List<CitiesGameRecordDto>> getRecords() {
        return status(HttpStatus.OK).body(service.getAllOfUser());
    }

    @GetMapping("/all")
    public ResponseEntity<List<CitiesGameRecordDto>> getAllRecords() {
        return status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/of-user/{name}")
    public ResponseEntity<List<CitiesGameRecordDto>> getRecordsByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getByUsername(username));
    }

    @GetMapping("/top-by/{name}")
    public ResponseEntity<CitiesGameRecordDto> getTopByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getBest(username));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid CitiesGameRecordDto citiesGameRecordDto) {
        log.info(citiesGameRecordDto.toString());
        service.save(citiesGameRecordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
