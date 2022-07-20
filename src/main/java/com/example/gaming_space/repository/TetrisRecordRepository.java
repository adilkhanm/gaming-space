package com.example.gaming_space.repository;

import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.TetrisRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TetrisRecordRepository extends JpaRepository<TetrisRecord, Long> {
    List<TetrisRecord> findByUser(AppUser user);
    List<TetrisRecord> findByUserOrderByScoreDesc(AppUser user);
}
