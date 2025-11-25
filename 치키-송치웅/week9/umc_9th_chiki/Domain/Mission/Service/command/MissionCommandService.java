package com.example.umc_9th_chiki.Domain.Mission.Service.command;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionRequestDTO;

public interface MissionCommandService {
    MemberMission challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDto request);
}