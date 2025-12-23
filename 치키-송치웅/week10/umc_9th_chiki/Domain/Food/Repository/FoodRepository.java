package com.example.umc_9th_chiki.Domain.Food.Repository;

import com.example.umc_9th_chiki.Domain.Store.Entity.Food; // Food 엔티티 경로 확인!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {
    Long countByIdIn(List<Long> foodIds);
}