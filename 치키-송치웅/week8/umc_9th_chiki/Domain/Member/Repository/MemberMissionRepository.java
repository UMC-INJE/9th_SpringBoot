package com.example.umc_9th_chiki.Domain.Member.Repository;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}