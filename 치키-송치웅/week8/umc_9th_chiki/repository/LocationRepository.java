package com.example.umc_9th_chiki.repository;

import com.example.umc_9th_chiki.Domain.Store.Entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
}