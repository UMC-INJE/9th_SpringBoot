package com.example.umc9th.domain.mission.service.command;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.req.MemberMissionReqDTO;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.entity.Mission;
import com.example.umc9th.domain.mission.exception.MemberMissionException;
import com.example.umc9th.domain.mission.exception.code.MemberMissionErrorCode;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import com.example.umc9th.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository repository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMissionResDTO.CreateDTO challenge(MemberMissionReqDTO.CreateDTO dto) {

        Member member = memberRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("임시 멤버 없음"));

        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new RuntimeException("미션 없음"));

        MemberMission mm = MemberMissionConverter.toEntity(member, mission);
        repository.save(mm);

        return MemberMissionConverter.toCreateDTO(mm);
    }

    @Override
    @Transactional
    public MemberMissionResDTO.CompleteDTO completeMission(Long memberMissionId) {

        MemberMission mm = repository.findById(memberMissionId)
                .orElseThrow(() -> new MemberMissionException(MemberMissionErrorCode.NOT_FOUND));

        if (mm.getIsComplete()) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_COMPLETED);
        }

        mm.complete();

        repository.save(mm);

        return MemberMissionConverter.toCompleteDTO(mm);
    }
}
