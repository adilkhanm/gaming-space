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
public class MinesweeperRecordDto {
    private Long recordId;
    private Integer score;
    private Integer timeSpent;
    private String flagWasUsed;
    private String username;
    private Instant date;
}
