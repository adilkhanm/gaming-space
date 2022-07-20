package com.example.gaming_space.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TetrisRecordDto {
    private Long recordId;
    private Integer score;
    private Integer timePlayed;
    private Integer tetrisNumber;
    private Integer triplesNumber;
    private Integer twosNumber;
    private Integer linesCleared;
    private String username;
    private Instant date;
}
