package com.example.umc_9th_chiki.Domain.Mission.Service.command;

import com.example.umc_9th_chiki.Domain.Member.Entity.Mapping.MemberMission;
import com.example.umc_9th_chiki.Domain.Member.Entity.Member;
import com.example.umc_9th_chiki.Domain.Mission.Converter.MissionConverter;
import com.example.umc_9th_chiki.Domain.Mission.Dto.MissionRequestDTO;
import com.example.umc_9th_chiki.Domain.Mission.Entity.Mission;
import com.example.umc_9th_chiki.Domain.Mission.Enums.MissionStatus;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberMissionRepository;
import com.example.umc_9th_chiki.Domain.Member.Repository.MemberRepository;
import com.example.umc_9th_chiki.Domain.Mission.Repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService {

    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(Long missionId, MissionRequestDTO.ChallengeMissionDto request) {


        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new RuntimeException("미션을 찾을 수 없습니다."));

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new RuntimeException("멤버를 찾을 수 없습니다."));

        if (memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                request.getMemberId(), missionId, MissionStatus.CHALLENGING)) {
            throw new RuntimeException("이미 도전 중인 미션입니다.");
        }

        MemberMission memberMission = MissionConverter.toMemberMission(member, mission);
        return memberMissionRepository.save(memberMission);
    }
}