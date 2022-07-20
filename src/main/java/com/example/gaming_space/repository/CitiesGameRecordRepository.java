package com.example.gaming_space.repository;

import com.example.gaming_space.model.AppUser;
import com.example.gaming_space.model.CitiesGameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CitiesGameRecordRepository extends JpaRepository<CitiesGameRecord, Long> {
    List<CitiesGameRecord> findByUser(AppUser user);
    List<CitiesGameRecord> findByUserOrderByScoreDesc(AppUser user);
}
