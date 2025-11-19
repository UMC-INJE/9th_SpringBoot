package com.example.umc_9th_chiki.Domain.Member.Repository;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberFood; // MemberFood 엔티티 경로 확인!
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberFoodRepository extends JpaRepository<MemberFood, Long> {
}