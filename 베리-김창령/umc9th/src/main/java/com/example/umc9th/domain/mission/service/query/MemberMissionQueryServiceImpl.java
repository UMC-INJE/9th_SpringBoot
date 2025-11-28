package com.example.umc9th.domain.mission.service.query;

import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.domain.mission.converter.MemberMissionConverter;
import com.example.umc9th.domain.mission.dto.res.MemberMissionResDTO;
import com.example.umc9th.domain.mission.entity.MemberMission;
import com.example.umc9th.domain.mission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMissionResDTO.MyMissionListDTO getMyProgressMissions(Long memberId, Integer page) {

        int pageIndex = page - 1;
        PageRequest pageRequest = PageRequest.of(pageIndex, 10);

        Page<MemberMission> result =
                memberMissionRepository.findByMember_IdAndIsCompleteOrderByCreatedAtDesc(
                        memberId, false, pageRequest
                );

        return MemberMissionConverter.toMyMissionListDTO(result);
    }
}

