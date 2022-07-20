package com.example.gaming_space.controller;

import com.example.gaming_space.dto.TetrisRecordDto;
import com.example.gaming_space.service.TetrisGameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/tetris")
@AllArgsConstructor
@Slf4j
public class TetrisGameController {

    private final TetrisGameService service;

    @GetMapping
    public ResponseEntity<List<TetrisRecordDto>> getRecords() {
        return status(HttpStatus.OK).body(service.getAllOfUser());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TetrisRecordDto>> getAllRecords() {
        return status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/of-user/{name}")
    public ResponseEntity<List<TetrisRecordDto>> getRecordsByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getByUsername(username));
    }

    @GetMapping("/top-by/{name}")
    public ResponseEntity<TetrisRecordDto> getTopByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getBest(username));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid TetrisRecordDto tetrisRecordDto) {
        service.save(tetrisRecordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
