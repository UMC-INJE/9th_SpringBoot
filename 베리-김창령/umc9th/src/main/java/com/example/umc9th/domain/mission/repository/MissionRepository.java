package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStoreLocationNameAndDeadlineGreaterThanEqualOrderByCreatedAtDesc(String locationName, LocalDate today, Pageable pageable);
}
