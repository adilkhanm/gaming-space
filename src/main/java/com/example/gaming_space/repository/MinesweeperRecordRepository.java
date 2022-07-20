package com.example.gaming_space.repository;

import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.MinesweeperRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MinesweeperRecordRepository extends JpaRepository<MinesweeperRecord, Long> {
    List<MinesweeperRecord> findByUser(AppUser user);
    List<MinesweeperRecord> findByUserOrderByScoreDesc(AppUser user);
}
