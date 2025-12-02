package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.MemberMission;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Page<MemberMission> findByMember_IdAndIsCompleteOrderByCreatedAtDesc(
            Long memberId,
            boolean isComplete,
            Pageable pageable
    );
}
