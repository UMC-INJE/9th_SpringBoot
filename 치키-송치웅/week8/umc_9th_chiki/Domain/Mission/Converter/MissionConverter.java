package com.example.umc_9th_chiki.Domain.Mission.Converter;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionResponseDTO;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;

public class MissionConverter {

    public static MissionResponseDTO.ChallengeMissionResultDto toChallengeMissionResultDto(MemberMission memberMission) {
        return MissionResponseDTO.ChallengeMissionResultDto.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt())
                .build();
    }

    public static MemberMission toMemberMission(Member member, Mission mission) {
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING) // 처음엔 '도전 중' 상태로 설정
                .build();
    }
}