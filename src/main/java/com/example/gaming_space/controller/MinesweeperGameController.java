package com.example.gaming_space.controller;

import com.example.gaming_space.dto.MinesweeperRecordDto;
import com.example.gaming_space.service.MinesweeperGameService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("api/minesweeper")
@AllArgsConstructor
@Slf4j
public class MinesweeperGameController {

    private final MinesweeperGameService service;

    @GetMapping
    public ResponseEntity<List<MinesweeperRecordDto>> getRecords() {
        return status(HttpStatus.OK).body(service.getAllOfUser());
    }

    @GetMapping("/all")
    public ResponseEntity<List<MinesweeperRecordDto>> getAllRecords() {
        return status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/of-user/{name}")
    public ResponseEntity<List<MinesweeperRecordDto>> getRecordsByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getByUsername(username));
    }

    @GetMapping("/top-by/{name}")
    public ResponseEntity<MinesweeperRecordDto> getTopByUsername(@PathVariable("name") String username) {
        return status(HttpStatus.OK).body(service.getBest(username));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody @Valid MinesweeperRecordDto minesweeperRecordDto) {
        service.save(minesweeperRecordDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
